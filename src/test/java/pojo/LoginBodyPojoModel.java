package pojo;

public class LoginBodyPojoModel {

    //String body = "data: { id: 2, email: \"janet.weaver@reqres.in\", first_name: \"Janet\", last_name:" +
    //                " \"Weaver\", avatar: \"https://reqres.in/img/faces/2-image.jpg\" }, support: { url:" +
    //                " \"https://reqres.in/#support-heading\", text: \"To keep ReqRes free, " +
    //                "contributions towards server costs are appreciated!\" } }";
    //String body = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";
    private String password;
    private String email;
    private String bodyNull;

    public String getBodyNull() {
        return bodyNull;
    }

    public void setBodyNull(String bodyNull) {
        this.bodyNull = bodyNull;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
