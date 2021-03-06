import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.*;
import net.dv8tion.jda.api.EmbedBuilder;

public class DiscordMessages extends ListenerAdapter {
    
    public static String PrintRGB(int R, int G, int B, String message) {
        return ("\033[38;2;"+R+";"+G+";"+B+"m" + message + "\u001B[0m");
    }
        
	// TEXT COLOURS
    public static final String ANSI_RGB_START = "\033[38;2;";
    public static final String ANSI_RGB_END = "m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	// BACKGROUND COLOURS
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		JDA jda = event.getJDA();
		User author = event.getAuthor();
		Message message = event.getMessage();
        Guild guild = event.getGuild();
		MessageChannel channel = event.getChannel();
        String msg = message.getContentDisplay();
        String guildColour = "";
        String channelColour = "";
		if (author.isBot() == true) {
			return;
		} else {
			if (event.isFromType(ChannelType.TEXT)) {
				
                // DEFAULT FUNCTIONS
				if (event.getChannel().getId().equals("000")) {                       // REPLACE 000 WITH ANY CHANNEL YOU DONT WANT PRINTED
				} else {
					// if (guild.getName().equals("$$$$$$$$$$")) {                       PLACE DISCORD NAME HERE TO HIGHLIGHT CHAT LOG
                    //     guildColour = ANSI_RGB_START + "R;G;B" + ANSI_RGB_END;        REPLACE R G B WITH 0-256 FOR HIGHLIGHT COLOUR
                    // }

					System.out.println("[" + guildColour + event.getGuild().getName() + ": " + event.getChannel().getName() + "] " + ANSI_RESET + author.getName() + ": " + msg);
				}
				
                // COMMANDS
                if (message.equals("!help")) {
                    channel.sendMessage("This is a help command");
                }
			}
		}
	}

	public static void PostTwitchLive(EmbedBuilder goingLiveEmbed, String streamName) {
		String goingLiveNow = (streamName + " is going live now");
        if (streamName.equals(TwitchManager.streamerPing)) {
            goingLiveNow = goingLiveNow; // + " <@&000>";                       PLACE ROLE ID HERE FOR GOING LIVE PING ANOUNCMENTS
        }
        CerpBot.jda.getTextChannelById("000").sendMessage(goingLiveNow).embed(goingLiveEmbed.build()).queue(); // REPLACE ### WITH GOING LIVE CHANNEL
		goingLiveEmbed.clear();
	}
}
