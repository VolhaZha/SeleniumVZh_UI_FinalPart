package util;

public enum PropertyKey {
    FIRSTNAME("firstName"),
    LASTNAME("lastName"),
    EMAIL("email"),

    EMAILEXIST("emailExist"),

    PASSWORD("password"),
    URLCREATE ("URLcreate"),

    URLMAIN ("URLmain"),
    URLADRBOOK ("URLadrbook"),
    PHONE ("Phone"),
    STREET ("Street"),
    CITY ("City"),
    STATE ("State"),
    ZIP ("Zip"),
    COUNTRY ("Country"),

    USERNAMESL("userNameSL"),
    ACCESSKEYSL("accessKeySL"),
    URLSL("urlSL"),
    EXECUTIONMODE ("executionMode"),
    BROWSERNAME ("browserName"),
    PRODUCT1 ("product1"),
    PRODUCT2 ("product2"),
    PRODUCT3 ("product3");
    private final String key;

    PropertyKey (String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}