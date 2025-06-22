package minu.coffee.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class LoginDto {

    public static class Register {

        @Getter
        @Setter
        public static class Request {
            @NotNull
            private Long shopInfoId;
            @NotBlank
            private String id;
            @NotBlank
            private String password;
            @NotBlank
            private String firstName;
            @NotBlank
            private String lastName;
            private String email;
            private String phoneNumber;
        }

        @Getter
        @Setter
        @AllArgsConstructor
        public static class Response {
            private String token;
        }
    }

    public static class Login{

        @Getter
        @Setter
        @NotBlank
        public static class Request {
            private String id;
            private String password;
        }

        @Getter
        @Setter
        @AllArgsConstructor
        public static class Response {
            private String token;
        }
    }

}
