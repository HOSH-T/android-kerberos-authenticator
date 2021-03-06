/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.apps.work.kerberosauthenticator;

import static com.google.android.apps.work.kerberosauthenticator.Constants.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/** Show the authentication status for the current account. */
public class AuthenticatorStatusActivity extends BaseAuthenticatorActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // If we do not have account configuration available, always start the decline activity.
    if (!accountConfiguration.hasManagedConfigs()) {
      startActivity(DeclineAddingAccountActivity.getDeclineIntentDueToConfigMissing(this, null));
      finish();
      return;
    }

    setContentView(R.layout.authenticator);

    boolean isLaunchedByUser = Intent.ACTION_MAIN.equals(getIntent().getAction());
    // Initialise the UI, showing a "Dismiss" button.
    initUI(isLaunchedByUser, "");

    // If only the status is shown, the activity remains open until the user taps the dismiss
    // button to finish it.
    Log.d(TAG, "Finished creating status activity.");
  }
}
