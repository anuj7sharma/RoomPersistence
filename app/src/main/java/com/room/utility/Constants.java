package com.room.utility;

/**
 * author by Anuj Sharma on 12/4/2017.
 */

public interface Constants {
    String BaseURL = "https://api.qwant.com/";

    String IMAGE_SEARCH_API = "api/search/images";
    String VIDEO_SEARCH_API = "api/search/videos";

    interface INTENT_ACTIONS{
        String DESTINATION = "destination";
        String SCREEN_SEARCH = "search_screen";
        String SCREEN_DETAIL = "detail_screen";

        String data_search_value = "search_value";
        String data_image_obj = "image_obj";

    }
    interface BroadcastIntent{
        String UPDATE_FOVOURITE = "update_favourite";
    }

}
