function getCombBySum(array,sum,tolerance,targetCount){
  var util = {
    /*
      get combination from array
      arr: target array
      num: combination item length
      return: one array that contain combination arrays
	  调用说明：
      array: 数据源数组。必选。
      sum: 相加的和。必选。
      tolerance: 容差。如果不指定此参数，则相加的和必须等于sum参数，指定此参数可以使结果在容差范围内浮动。可选。
      targetCount: 操作数数量。如果不指定此参数，则结果包含所有可能的情况，指定此参数可以筛选出固定数量的数相加，假如指定为3，那么结果只包含三个数相加的情况。可选。
      返回值：返回的是数组套数组结构，内层数组中的元素是操作数，外层数组中的元素是所有可能的结果。
    */
    getCombination: function(arr, num) {
      var r=[];
      (function f(t,a,n)
      {
          if (n==0)
          {
              return r.push(t);
          }
          for (var i=0,l=a.length; i<=l-n; i++)
          {
              f(t.concat(a[i]), a.slice(i+1), n-1);
          }
      })([],arr,num);
      return r;
    },
    //take array index to a array
	//取数组索引
    getArrayIndex: function(array) {
      var i = 0,
        r = [];
      for(i = 0;i<array.length;i++){
        r.push(i);
      }
      
      return r;
    }
  },logic = {
    //sort the array,then get what's we need
	//排序数组，获取需要的部分
    init: function(array,sum) {
      //clone array
	  //复制到新数组
      var _array = array.concat(),
      r = [],
      i = 0;
      //sort by asc
	  //正序
      _array.sort(function(a,b){
        return a - b;
      });
      //get all number when it's less than or equal sum
	  //得到所有小于或等于总和的数字
      for(i = 0;i<_array.length;i++){
        if(_array[i]<=sum){
          r.push(_array[i]);
        }else{
          break;
        }
      }
      
      return r;
    },
    //important function
	//关键部分
    core: function(array,sum,arrayIndex,count,r){
      var i = 0,
        k = 0,
        combArray = [],
        _sum = 0,
        _cca = [],
        _cache = [];
      
      if(count == _returnMark){
        return;
      }
      //get current count combination
	  //得到当前总组合数
      combArray = util.getCombination(arrayIndex,count);
      for(i = 0;i<combArray.length;i++){
        _cca = combArray[i];
        _sum = 0;
        _cache = [];
        //calculate the sum from combination
		//从组合中计算总和
        for(k = 0;k<_cca.length;k++){
          _sum += array[_cca[k]];
          _cache.push(array[_cca[k]]);
        }
        if(Math.abs(_sum-sum) <= _tolerance){
          r.push(_cache);
        }      
      }
      
      logic.core(array,sum,arrayIndex,count-1,r);
    }
    
  },
    r = [],
    _array = [],
    _targetCount = 0,
    _tolerance = 0,
    _returnMark = 0;
  
  //check data
  //检查入参
  _targetCount = targetCount || _targetCount;
  _tolerance = tolerance || _tolerance;
  
  _array = logic.init(array,sum);
  if(_targetCount){
    _returnMark = _targetCount-1;
  }
  
  logic.core(_array,sum,util.getArrayIndex(_array),(_targetCount || _array.length),r);
  
  return r;
}