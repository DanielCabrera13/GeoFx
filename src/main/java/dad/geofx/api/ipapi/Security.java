
package dad.geofx.api.ipapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Security {
	
	@SerializedName("is_proxy")
    @Expose
    private Boolean isProxy;
    
	@SerializedName("proxy_type")
    @Expose
    private Object proxyType;
    
	@SerializedName("is_crawler")
    @Expose
    private Boolean isCrawler;
   
	@SerializedName("crawler_name")
    @Expose
    private Object crawlerName;

	@SerializedName("crawler_type")
    @Expose
    private Object crawlerType;
    
	@SerializedName("is_tor")
    @Expose
    private Boolean isTor;
    
	@SerializedName("threat_level")
    @Expose
    private String threatLevel;
    
	@SerializedName("threat_types")
    @Expose
    private Object threatTypes;
    
   
    public Boolean getIsProxy() {
        return isProxy;
    }

    
    public void setIsProxy(Boolean isProxy) {
        this.isProxy = isProxy;
    }

    
    public Object getProxyType() {
        return proxyType;
    }

   
    public void setProxyType(Object proxyType) {
        this.proxyType = proxyType;
    }

   
    public Boolean getIsCrawler() {
        return isCrawler;
    }

   
    public void setIsCrawler(Boolean isCrawler) {
        this.isCrawler = isCrawler;
    }

    
    public Object getCrawlerName() {
        return crawlerName;
    }

    
    public void setCrawlerName(Object crawlerName) {
        this.crawlerName = crawlerName;
    }

    
    public Object getCrawlerType() {
        return crawlerType;
    }

    
    public void setCrawlerType(Object crawlerType) {
        this.crawlerType = crawlerType;
    }

    
    public Boolean getIsTor() {
        return isTor;
    }

    
    public void setIsTor(Boolean isTor) {
        this.isTor = isTor;
    }

    
    public String getThreatLevel() {
        return threatLevel;
    }

    
    public void setThreatLevel(String threatLevel) {
        this.threatLevel = threatLevel;
    }

    
    public Object getThreatTypes() {
        return threatTypes;
    }

    
    public void setThreatTypes(Object threatTypes) {
        this.threatTypes = threatTypes;
    }



}
