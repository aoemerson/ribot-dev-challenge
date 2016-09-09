package io.github.aoemerson.riapidevchallenge.presenter.detail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import io.github.aoemerson.riapidevchallenge.model.Ribot;
import io.github.aoemerson.riapidevchallenge.util.RibotTestFactory;
import io.github.aoemerson.riapidevchallenge.view.detail.RibotDetailView;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RibotDetailPresenterTests {

    @Mock RibotDetailView ribotDetailView;

    @Test
    public void shouldGetBundledDataOnAttach() {
        DetailPresenter presenter = new RibotDetailPresenter();
        presenter.attachView(ribotDetailView);
        verify(ribotDetailView).getBundledRibots();
        verify(ribotDetailView).getBundledTargetRibot();
    }

    @Test
    public void shouldUpdateViewOnRequestDetail() {
        DetailPresenter presenter = new RibotDetailPresenter();
        List<Ribot> testRibots = RibotTestFactory.createTestRibots(10);
        when(ribotDetailView.getBundledRibots()).thenReturn(testRibots);
        presenter.attachView(ribotDetailView);
        presenter.requestDetail();
        verify(ribotDetailView).setRibots(eq(testRibots));
    }
}
