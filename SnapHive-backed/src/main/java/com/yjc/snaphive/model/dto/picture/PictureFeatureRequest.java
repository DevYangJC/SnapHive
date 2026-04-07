package com.yjc.snaphive.model.dto.picture;

import lombok.Data;

@Data
public class PictureFeatureRequest {
    /**
     * 图片id
     */
    private Long id;

    /**
     * 是否精�?0-非精�?1-精�?
     */
    private Integer isFeature;
}
