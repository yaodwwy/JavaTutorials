/*
package utils;

import cn.gomro.mid.core.common.Constants;
import cn.gomro.mid.core.common.message.ReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

*/
/**
 * Created by momo on 16/5/30.
 *//*

public class JpaUtils {

    final static Logger logger = LoggerFactory.getLogger(JpaUtils.class);

    public static int queryCount(EntityManager em, String queryCount) {
        try {
            Query query = em.createQuery(queryCount);
            query.setHint(Constants.JPA_CACHEABLE, true);
            return new Long((long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            logger.error(e.getMessage());
            if(e.getCause()!=null){
                logger.error(e.getCause().getMessage());
            }
        } finally {
            em.clear();
        }

        return -1;
    }

    public static <T> T querySingleResult(EntityManager em, String querySingleResult) {

        try {
            Query query = em.createQuery(querySingleResult);
            query.setHint(Constants.JPA_CACHEABLE,true);
            List<T> rs = query.setMaxResults(2).getResultList();
            if (rs != null & rs.size() == 1) {
                return rs.get(0);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            if(e.getCause()!=null){
                logger.error(e.getCause().getMessage());
            }
        } finally {
            em.clear();
        }
        return null;
    }

    public static <T> List<T> queryShortResultList(EntityManager em, String query) {

        try {
            Query emQuery = em.createQuery(query);
            emQuery.setHint(Constants.JPA_CACHEABLE,true);
            return emQuery.getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage());
            if(e.getCause()!=null){
                logger.error(e.getCause().getMessage());
            }
        } finally {
            em.clear();
        }

        return null;
    }

    public static <V> ReturnMessage<List<V>> queryPaged(EntityManager em, String queryCount, String queryList, int page, int size) {

        List<V> list;

        try {
            Query emQuery = em.createQuery(queryCount);
            emQuery.setHint(Constants.JPA_CACHEABLE,true);
            int count = new Long((long) emQuery.getSingleResult()).intValue();
            Query query = em.createQuery(queryList);
            query.setHint(Constants.JPA_CACHEABLE,true);
            int pages = size == 0 ? 0 : (count / size);
            if (count % size != 0) pages++;
            if (page < 1) page = 1;
            if (page > pages) page = pages;

            int start = (page - 1) * size;
            if (count > 0 && start < count) {
                query.setFirstResult(start);
                query.setMaxResults(size);
            }
            list = query.getResultList();

            return ReturnMessage.message(count, String.valueOf(size), list);
        } catch (Exception e) {
            logger.error(e.getMessage());
            if(e.getCause()!=null){
                logger.error(e.getCause().getMessage());
            }
        } finally {
            em.clear();
        }

        return ReturnMessage.failed();
    }
}
*/
