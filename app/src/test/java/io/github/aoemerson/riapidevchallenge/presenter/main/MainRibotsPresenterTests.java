package io.github.aoemerson.riapidevchallenge.presenter.main;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import aoemerson.github.io.riapidevchallenge.R;
import io.github.aoemerson.riapidevchallenge.api.RibotsClient;
import io.github.aoemerson.riapidevchallenge.model.Ribot;
import io.github.aoemerson.riapidevchallenge.util.RibotTestFactory;
import io.github.aoemerson.riapidevchallenge.view.main.OpenRibotDetailCommand;
import io.github.aoemerson.riapidevchallenge.view.main.RibotsView;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainRibotsPresenterTests {

    @Mock
    RibotsClient ribotsClient;

    @Mock
    RibotsView ribotsView;

    @Before
    public void setup() {

    }

    @Test
    public void loadAndDisplayRibots() {
        MainRibotsPresenter presenter = new MainRibotsPresenter(ribotsView, ribotsClient);
        presenter.requestRibots();
        verify(ribotsView, times(1)).showLoading(eq(true));
        verify(ribotsClient, times(1)).getRibots(eq(presenter));
    }

    @Test
    public void shouldUpdateViewOnRibotsLoaded() {
        MainRibotsPresenter presenter = new MainRibotsPresenter(ribotsView, ribotsClient);

        List<Ribot> testRibots = RibotTestFactory.createTestRibots(10);
        presenter.onRibotsLoaded(testRibots);
        verify(ribotsView, times(1)).showLoading(eq(false));
        verify(ribotsView, times(1)).showRibots(eq(testRibots));
    }

    @Test
    public void shouldShowErrorOnRibotsLoadingException() {
        MainRibotsPresenter presenter = new MainRibotsPresenter(ribotsView, ribotsClient);
        Exception testException = new Exception();
        presenter.onRibotsLoadError(testException);
        verify(ribotsView, times(1)).showLoading(eq(false));
        verify(ribotsView, times(1)).showError(eq(R.string.error_msg_generic));
    }

    @Test
    public void shouldShowErrorOnRibotsLoadingIOException() {
        MainRibotsPresenter presenter = new MainRibotsPresenter(ribotsView, ribotsClient);
        Exception testException = new IOException("Failed to connect to Ribot API");
        presenter.onRibotsLoadError(testException);
        verify(ribotsView, times(1)).showLoading(eq(false));
        verify(ribotsView, times(1)).showError(eq(R.string.error_msg_ribot_connection_failed));
    }

    @Test
    public void shouldUpdateViewOnRibotClicked() {
        MainRibotsPresenter presenter = new MainRibotsPresenter(ribotsView, ribotsClient);
        presenter.ribots = RibotTestFactory.createTestRibots(5); // Inject for test purposes
        presenter.ribotClicked(1);
        verify(ribotsView, times(1)).showRibotDetail(any(OpenRibotDetailCommand.class));
    }

    @Test
    public void shouldAttachAndDetachView() {
        MainRibotsPresenter presenter = new MainRibotsPresenter();
        assertThat(presenter.ribotsView, is(nullValue()));
        presenter.attachView(ribotsView);
        assertThat(presenter.ribotsView, is(not(nullValue())));
        assertThat(ribotsView, is(equalTo(presenter.ribotsView)));

        presenter.detachView();
        assertThat(ribotsView, is(not(equalTo(presenter.ribotsView))));
        assertThat(presenter.ribotsView, is(nullValue()));
    }

    @Test
    public void shouldShowErrorOnNoRibots() {
        MainRibotsPresenter presenter = new MainRibotsPresenter(ribotsView, ribotsClient);
        presenter.onRibotsLoaded(new ArrayList<Ribot>());
        int expectedErrMsgResId = R.string.error_msg_no_ribots_found;
        verify(ribotsView, times(1)).showError(eq(expectedErrMsgResId));
    }

    @Test
    public void shouldShowErrorOnNullRibots() {
        MainRibotsPresenter presenter = new MainRibotsPresenter(ribotsView, ribotsClient);
        presenter.onRibotsLoaded(null);
        int expectedErrMsgResId = R.string.error_msg_no_ribots_found;
        verify(ribotsView, times(1)).showError(eq(expectedErrMsgResId));
    }
}
