package com.example.yourpc.tyyargae;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Helloworld.Builder builder = new Helloworld.Builder(AndroidHttp.newCompatibleTransport(),
//                new AndroidJsonFactory(), null);
//
//        new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Manfred"));

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
