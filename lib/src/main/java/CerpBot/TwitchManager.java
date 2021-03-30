import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.events.ChannelGoLiveEvent;
import com.github.twitch4j.helix.domain.User;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.util.Collections;
import java.util.Locale;

public class TwitchManager {
    private static final String twitchId = "";                                        // REPLACE WITH TWITCH BOT ID
    private static final String twitchSecret = "";                                    // REPLACE WITH TWITCH BOT SECRET
    private static final String[] streamer = {"ins0mniasr", "sozapidgeon"};           // ADD lowercase STREAMERS HERE (TAKE USERNAME FROM TWITCH BROWSER LINK)
    public static final String streamerPing = "ins0mniasr";                           // PROBABLY REPLACE THIS SO SOMNIS STREAMS DONT DO YOUR LIVE PING

    private static final Color goingLiveColor = new Color(100, 65, 165);

    public static void StartTwitchClient() {
        TwitchClient twitchBot = TwitchClientBuilder.builder()
                .withEnableHelix(true)
                .withEnableKraken(true)
                .withClientId(twitchId)
                .withClientSecret(twitchSecret)
                .build();
        
        for (int i = 0; i < streamer.length; i++) {
            twitchBot.getClientHelper().enableStreamEventListener(streamer[i]);
            System.out.println("Stream Listener started for: " + streamer[i]);
        }

        twitchBot.getEventManager().onEvent(ChannelGoLiveEvent.class, event -> {

            String streamName = event.getChannel().getName();
            String url = ("https://www.twitch.tv/" + streamName);
            User streamUser = twitchBot.getHelix().getUsers(null, null, Collections.singletonList(streamName)).execute().getUsers().get(0);
            String streamUserPictureUrl = streamUser.getProfileImageUrl();
            String streamTitle = event.getStream().getTitle();
            String streamGame = event.getStream().getGameName();
            
            System.out.println("Stream started for: " + streamName + ": " + streamTitle);
		    
            EmbedBuilder goingLive = new EmbedBuilder();
        	
            goingLive.setColor(goingLiveColor);
            goingLive.setTitle(streamTitle, url);
            goingLive.setAuthor(streamName + " just went live!", null, streamUserPictureUrl);
            goingLive.setThumbnail(streamUserPictureUrl);
            goingLive.addField("Game", streamGame, false);

            DiscordMessages.PostTwitchLive(goingLive, streamName);

            goingLive.clear();

        });
    }
}
