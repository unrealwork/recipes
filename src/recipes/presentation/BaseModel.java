package recipes.presentation;

import recipes.persistance.BaseDTO;

public interface BaseModel<T extends BaseDTO<?>> {
    T toDto();
}
