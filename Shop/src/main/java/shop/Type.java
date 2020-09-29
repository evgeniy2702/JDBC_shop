package shop;

public enum Type {
    INDUSTRIAL("Пром_товары"), PRODUCT("Продуктовые_товары");

    String string;

    Type(final String string) {
        this.string = string;
    }

    public String getString() {
        return this.string;
    }
}
