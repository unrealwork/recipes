package recipes.persistance;

import recipes.presentation.BaseModel;

public interface BaseDTO<T extends BaseModel<?>> {
    T toModel();
}
