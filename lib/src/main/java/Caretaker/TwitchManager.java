import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.events.ChannelGoLiveEvent;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.util.Locale;

public class TwitchManager {
    public static final String twitchId = "32p3en14qg28y227gim6phritbu1la";
    public static final String twitchSecret = "q6z3biu1wbo26lgrczly90thm1ht2g";
    public static final String targetStreamer = "sozapidgeon";
    public static final Color SomniBlue = new Color(0, 102, 255);

    public static void StartTwitchClient() {
        TwitchClient twitchBot = TwitchClientBuilder.builder()
                .withEnableHelix(true)
                .withEnableKraken(true)
                .withClientId(twitchId)
                .withClientSecret(twitchSecret)
                .build();

        twitchBot.getClientHelper().enableStreamEventListener("sozapidgeon");
        twitchBot.getClientHelper().enableStreamEventListener("honeybirdie");
        System.out.println("StreamEventListener active on: sozapidgeon *2");
        twitchBot.getEventManager().onEvent(ChannelGoLiveEvent.class, event -> {
            String streamName = event.getChannel().getName();
            User streamUser = event.getUser();
            String streamTitle = event.getStream().getTitle();
            System.out.println("Stream started for " + streamName + ": " + streamTitle);
		    EmbedBuilder goingLive = new EmbedBuilder();
        	goingLive.setColor(SomniBlue);

//            embed=discord.Embed(title=Hollow Knight Al2ba%  and then Nice% Speedruns, url=https://www.twitch.tv/ins0mniasr, color=0x221fff)
//            embed.set_author(name=ins0mniaSR, url=https://www.twitch.tv/ins0mniasr, icon_url=https://images-ext-2.discordapp.net/external/WsMRlkdt8n8CMFR2E8tH-YAt9SNMo5DnDjrIHH7bKpI/https/static-cdn.jtvnw.net/jtv_user_pictureM#M#M#M#M#s/1e765843-8642-41ef-b62e-19fec401eb46-profile_image-300x300.png)
//            embed.set_thumbnail(url=https://images-ext-2.discordapp.net/external/WsMRlkdt8n8CMFR2E8tH-YAt9SNMo5DnDjrIHH7bKpI/https/static-cdn.jtvnw.net/jtv_user_pictures/1e765843-8642-41ef-b62e-19fec401eb46-profile_image-300x300.png?width=120&height=120)
//            embed.add_field(name=Game, value=Hollow Knight, inline=True)
//            await ctx.send(embed=embed)



            String url = ("https://www.twitch.tv/" + streamName);
            goingLive.setTitle(streamTitle, url);
            goingLive.setAuthor(streamName + " just went live!", "");
            goingLive.setThumbnail("https://images-ext-2.discordapp.net/external/WsMRlkdt8n8CMFR2E8tH-YAt9SNMo5DnDjrIHH7bKpI/https/static-cdn.jtvnw.net/jtv_user_pictures/1e765843-8642-41ef-b62e-19fec401eb46-profile_image-300x300.png");
            goingLive.addField("Game", event.getStream().getGameName(), false);

            DiscordMessages.PostTwitchLive(goingLive);
            goingLive.clear();
        });
    }
}
