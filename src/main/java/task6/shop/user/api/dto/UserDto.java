package task6.shop.user.api.dto;

import task6.shop.user.domain.User;

public class UserDto {

    private Integer id;
    private String email;
    private String profileDescription;

    public UserDto(Integer id, String email, String profileDescription) {
        this.id = id;
        this.email = email;
        this.profileDescription = profileDescription;
    }

    public static UserDto fromUser(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getProfileDescription()
        );
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", profileDescription='" + profileDescription + '\'' +
                '}';
    }
}