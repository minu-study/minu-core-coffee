package minu.coffee.member;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class MemberDto {


    public static class AccumulatePoints {

        @Getter
        @Setter
        @ToString
        public static class Request {
            @NotNull
            private Integer point;
        }

    }

}