package me.reolcharm.ad.constant;

import lombok.Getter;

/**
 * @author K1
 */

@Getter
public enum CreativeMetrialType {
    /**
     * the type of IMAGE
     */
    JPG(1, "jpg"),
    BMP(2, "bmp"),
    /**
     * the type of VIDEO
     */
    MP4(3, "mp4"),
    AVI(4, "avi"),
    /**
     * the type of TEXT
     */
    TXT(5, "txt"),
    /**
     * the type of AUDIO
     */
    MP3(6, "mp3");

    private int type;
    private String desc;

    CreativeMetrialType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
