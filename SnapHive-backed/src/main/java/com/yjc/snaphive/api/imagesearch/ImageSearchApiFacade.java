package com.yjc.snaphive.api.imagesearch;

import com.yjc.snaphive.api.imagesearch.model.ImageSearchResult;
import com.yjc.snaphive.api.imagesearch.sub.GetImageFirstUrlApi;
import com.yjc.snaphive.api.imagesearch.sub.GetImageListApi;
import com.yjc.snaphive.api.imagesearch.sub.GetImagePageUrlApi;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ImageSearchApiFacade {

    /**
     * 搜索图片
     * @param imageUrl
     * @return
     */
    public static List<ImageSearchResult> searchImage(String imageUrl) {
        String imagePageUrl = GetImagePageUrlApi.getImagePageUrl(imageUrl);
        String imageFirstUrl = GetImageFirstUrlApi.getImageFirstUrl(imagePageUrl);
        List<ImageSearchResult> imageList = GetImageListApi.getImageList(imageFirstUrl);
        return imageList;
    }

    public static void main(String[] args) {
        List<ImageSearchResult> imageList = searchImage("https://yuemu-picture-1328106169.cos.ap-chongqing.myqcloud.com//public/1866450683272450049/2025-01-04_KkXU1QdKykppCcG3.jpg");
        System.out.println("结果列表" + imageList);
    }
}
