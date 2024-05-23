package ru.praktikum_services.qa_scooter.courier;

public class CourierLoginData {
    private String login;
    private String password;

    public CourierLoginData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CourierLoginData from(CourierData courierData) {
        return new CourierLoginData(courierData.getLogin(), courierData.getPassword());
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
