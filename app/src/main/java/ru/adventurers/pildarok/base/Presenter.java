package ru.adventurers.pildarok.base;

/**
 * Created by Дмитрий on 20.04.2016.
 */
public interface Presenter<V extends MVPView> {

    void attachView(V mvpView);

    void detachView();
}
