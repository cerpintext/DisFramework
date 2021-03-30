import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.*;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class DiscordManager {
	private static final String token = "";                                           // REPLACE WITH YOUR DICORD BOT TOKEN
	

	public static JDA StartDiscordClient() throws InterruptedException, LoginException {
		JDABuilder builder = JDABuilder.createDefault(token);
		builder.setActivity(Activity.watching(""));                                   // CHANGE CUSTOM STATUS MESSAGE (CURRENTLY 'WATCHING' BUT CAN BE CHANGED
		builder.setStatus(OnlineStatus.ONLINE);
		builder.addEventListeners(new DiscordMessages());
		JDA jda = builder.build();
        jda.awaitReady();
        return jda;
	}
}
