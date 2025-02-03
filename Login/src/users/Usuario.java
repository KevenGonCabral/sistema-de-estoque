package users;
import java.io.Serializable;

public class Usuario implements Serializable {
    private String nickname;
    private String senha;

    public Usuario(String nickname, String senha) {
        this.nickname = nickname;
        this.senha = senha;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSenha() {
        return senha;
    }
}