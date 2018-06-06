### 环境依赖

> Git Bash
 
> NodeJS


    cd yaodwwy.github.io
    npm i -g hexo
    hexo init
    
    #git部署依赖
    npm install hexo-deployer-git --save
    
    #样式依赖
    npm install --save hexo-renderer-sass
    
    #Create SSH key
    ssh-keygen -t rsa -C yaodwwy@gmail.com
    ssh -T git@github.com
    
    #Clone with SSH
    git@github.com:yaodwwy/yaodwwy.github.io.git
    
    #清理
    hexo clean
    
    #部署
    hexo d -g
