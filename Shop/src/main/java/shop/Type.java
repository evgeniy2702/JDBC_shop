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

    public void setType (String string){
        if(string == this.string)
            Type.valueOf(string);
    }

}
