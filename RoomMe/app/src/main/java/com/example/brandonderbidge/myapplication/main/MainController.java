package com.example.brandonderbidge.myapplication.main;


public class MainController {
    private static final String TAG = "SellController";
    private MainActivity mainActivity;

    MainController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setsellActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


    /*private class LoginTask extends AsyncTask<LoginCmdData, Void, LoginResult> {
        @Override
        protected LoginResult doInBackground(LoginCmdData... params) {
            LoginResult loginResult;

            if (params[0].getClass().getSimpleName().equals("RegisterCmdData")) {
                RegisterCmdData registerCmdData = (RegisterCmdData) params[0];
                loginResult = ServerProxy.getInstance().register(registerCmdData.getUsername(), registerCmdData.getPassword(),
                        registerCmdData.getFirstName(), registerCmdData.getLastName(), FirebaseInstanceId.getInstance().getToken());
            } else {
                loginResult = ServerProxy.getInstance().login(params[0].getUsername(), params[0].getPassword(), FirebaseInstanceId.getInstance().getToken());
            }

            return loginResult;
        }

        @Override
        protected void onProgressUpdate(Void... result) {}

        @Override
        protected void onPostExecute(LoginResult result) {
            if (result.getMessage() == null) {
                onSuccess(result);
            } else {
                onError(result);
            }
        }
    }

    private void onSuccess(LoginResult result) {
        if (result.getAuthToken() == null) {
            Log.e(TAG, "AuthToken missing on successful return");
            loginActivity.createToast("There was an error with the server", Toast.LENGTH_SHORT);
        }
        if (result.getUsername() == null) {
            Log.e(TAG, "AuthToken missing on successful return");
            loginActivity.createToast("There was an error with the server", Toast.LENGTH_SHORT);
        }

        loginActivity.showProgressWheel(false);
        loginActivity.switchToLobbyActivity();
    }

    private void onError(LoginResult result) {
        Log.e(TAG, "login error:\n" + result.getMessage());
        loginActivity.showProgressWheel(false);
        loginActivity.createToast(result.getMessage(), Toast.LENGTH_LONG);
    }*/
}
