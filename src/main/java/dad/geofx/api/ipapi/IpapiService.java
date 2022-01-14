package dad.geofx.api.ipapi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IpapiService {

	private static final String BASE_URL = "https://ipapi.com/";
	private IpapiInterface service;

	public IpapiService() {

		ConnectionPool pool = new ConnectionPool(1, 1, TimeUnit.SECONDS);

		OkHttpClient client = new OkHttpClient.Builder().connectionPool(pool).build();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client)
				.addConverterFactory(GsonConverterFactory.create()).build();

		service = retrofit.create(IpapiInterface.class);
	}
	
	public IpapiMessage message(String ip) throws IOException {
		
		Response<IpapiMessage> response = service.ipInfo(ip).execute();
		
		IpapiMessage message = response.body();
		
		return message;
		
	}
}
