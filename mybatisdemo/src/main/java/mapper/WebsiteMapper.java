package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

import page.PageV0;
import po.Website;
import po.WebsitePOJO;
import po.WebsitePageQuery;

public interface WebsiteMapper {
    public List<WebsitePOJO> selectAllWebsite();

    // 方法名称需要和WebsiteMapper.xml文件中的id一致
    public int addWebsite(Website website);

    public List<Website> selectByParam(Website website);

    public List<Website> selectByName(@Param("name") String name);

    public int updateWebsiteByParam(Website website);

    public int deleteWebsiteById(@Param("id") int id);

    public List<Website> selectInAge(List<Integer> ages);

    public int findPageTotal(WebsitePageQuery qry);

    public List<Website> findPageByHand(WebsitePageQuery qry);
    public List<Website> findPageByPlugin(WebsitePageQuery qry);
    // public Page<Website> findPageByPlugin(WebsitePageQuery qry);
}
