package dad.geofx.api.ipify;


import retrofit2.Call;
import retrofit2.http.GET;

public interface IpifyInterface {
	
	@GET("?format=json")
	public Call<IpifyMessage> getIP();
}
