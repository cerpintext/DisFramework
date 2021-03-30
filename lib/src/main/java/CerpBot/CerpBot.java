import net.dv8tion.jda.api.JDA;

import javax.security.auth.login.LoginException;

public class CerpBot {
	public static JDA jda;

	public static void main(String[] args) {
		try {
			jda = DiscordManager.StartDiscordClient();
			TwitchManager.StartTwitchClient();
            System.out.println("Bot ready");
        } catch (LoginException | InterruptedException e) {
			e.getMessage();
		}
	}
}
