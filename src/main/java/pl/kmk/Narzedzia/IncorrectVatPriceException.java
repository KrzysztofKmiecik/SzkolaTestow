package pl.kmk.Narzedzia;

class IncorrectVatPriceException extends Throwable {
    private String message;

    public IncorrectVatPriceException(String message) {

        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
