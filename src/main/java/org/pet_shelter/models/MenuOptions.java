package org.pet_shelter.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MenuOptions {
    ADD_PET("1", "Add pet"),
    SHOW_ALL("2", "Show all pets"),
    TAKE_PET("3", "Take pet from shelter"),
    EXIT("4", "Exit");

    private final String key;
    private final String description;

    public static MenuOptions fromKey(String key) {
        for (MenuOptions option : values()) {
            if (option.key.equals(key)) {
                return option;
            }
        }
        return null;
    }
}
