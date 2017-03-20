package com.example.yourpc.tyyargae;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.appspot.tayyar_trial.pharmacyAPI.PharmacyAPI;
import com.appspot.tayyar_trial.pharmacyAPI.model.PharmacyViewCollection;
import com.appspot.tayyar_trial.restaurantAPI.RestaurantAPI;
import com.appspot.tayyar_trial.restaurantAPI.model.Restaurant;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

//        Observable.fromCallable(this::addRestaurant)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(s -> {
//                    Log.d(TAG, "s = " + s);
//                }, throwable -> Log.e(TAG, "onCreate: ", throwable));

        button.setOnClickListener(v -> Log.d(TAG, "getToken() = " + FirebaseInstanceId.getInstance().getToken()));

    }

    private PharmacyViewCollection getAllPharmacies() throws IOException {
        PharmacyAPI restaurantAPI = new PharmacyAPI.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                // options for running against local devappserver
                // - 10.0.2.2 is localhost's IP address in Android emulator
                // - turn off compression when running against local devappserver
//                .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                .setRootUrl("http://www.tayyar-trial.appspot.com/")
                .setGoogleClientRequestInitializer(clientRequest -> clientRequest.setDisableGZipContent(false))
                .build();

        return restaurantAPI.getAllPharmacies(0, 1).execute();
    }

    private Restaurant addRestaurant() throws IOException {
        RestaurantAPI restaurantAPI = new RestaurantAPI.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                .setRootUrl("http://10.0.2.2:8089/_ah/api/")
                //                .setRootUrl("http://10.0.2.2:8086/_ah/api/")
                //                .setRootUrl("https://tayyar-trial.appspot.com/")
                .setGoogleClientRequestInitializer(clientRequest -> clientRequest.setDisableGZipContent(false))
                .build();


        return restaurantAPI.createRestaurant("address", "hu@g.co", "https://tyyarstorage.blob.core.windows.net/merchantsimages/normandy.jpg",
                "nameA", "pass", "0110", 3).execute();
    }

    private void initView() {
        button = (Button) findViewById(R.id.button);
    }

    /*private class EndpointsAsyncTask extends AsyncTask<Object, Object, List<Customer>> {
        private Helloworld myApiService = null;

        @SafeVarargs
        @Override
        protected final List<Customer> doInBackground(Object... params) {
            if (myApiService == null) {  // Only do this once
                Helloworld.Builder builder = new Helloworld.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                myApiService = builder.build();
                try {
                    Restaurant execute =
                            myApiService.createRestaurant("add", "em@g.com", "na01", "pass", "0110", 25).execute();
//                    Log.d(TAG, "execute = " + execute);
                    Log.d(TAG, "id = " + execute.getId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                return myApiService.loadAllCustomers().execute().getItems();
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Customer> result) {
//            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }
    }*/
}
