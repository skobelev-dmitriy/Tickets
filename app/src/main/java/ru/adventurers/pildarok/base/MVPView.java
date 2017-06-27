package ru.adventurers.pildarok.base;

import ru.adventurers.pildarok.models.Error;
/**
 * Created by Дмитрий on 20.04.2016.
 */
public interface MVPView {
    void onError(Error error);
    void onProgress();

}
