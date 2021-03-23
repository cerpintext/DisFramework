import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.*;
import net.dv8tion.jda.api.EmbedBuilder;


public class DiscordMessages extends ListenerAdapter {

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
				if (event.getChannel().getId().equals("820279211704057927")) {
				} else {
					if (guild.getName().equals("ins0mnia's World")) {
                        guildColour = ANSI_RGB_START + "34;31;255" + ANSI_RGB_END;
                    } else if (guild.getName().equals("Soza's Cinema")) {
                        guildColour = ANSI_RGB_START + "177;0;0" + ANSI_RGB_END;
                    }

					System.out.println("[" + guildColour + event.getGuild().getName() + ": " + event.getChannel().getName() + "] " + ANSI_RESET + author.getName() + ": " + msg);
				}
				// COMMANDS
			}
		}
	}

	public static void PostTwitchLive(EmbedBuilder embed) {
//		Caretaker.jda.getTextChannelById("788926831948464148").sendMessage("Ins0mnia is going live now <@&748788513872412702>").queue();
		Caretaker.jda.getTextChannelById("788926831948464148").sendMessage(embed.build()).queue();
		embed.clear();
	}

	public static void PostMessage(String message) {
		Caretaker.jda.getTextChannelById("788926831948464148").sendMessage(message).queue();
	}
}
