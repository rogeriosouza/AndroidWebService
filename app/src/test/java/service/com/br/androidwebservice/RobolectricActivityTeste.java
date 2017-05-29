package service.com.br.androidwebservice;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)

public class RobolectricActivityTeste {
    private MainActivity mActivity;

    @Before
    public void setUp() throws Exception{

        mActivity = Robolectric.buildActivity(MainActivity.class).create().resume().get();
    }



    @Test
    public void checkActivityNotNull() throws Exception {
        assertNotNull(mActivity);

    }

    @Test
    public void checkJsonObjectNotNull() throws Exception{

        assertNotNull(mActivity.jsonobject);
    }


    @Test
    public void shouldCorrectAppNametActivity(){

        String appname = mActivity.getResources().getString(R.string.app_name);
        assertThat(appname,equalTo("AndroidWebService"));

    }


 /*   @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }*/
}