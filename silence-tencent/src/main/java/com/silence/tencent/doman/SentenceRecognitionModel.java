package com.silence.tencent.doman;

import com.tencentcloudapi.common.AbstractModel;
import lombok.Data;

import java.util.HashMap;

/**
 * 语句识别模型
 */
@Data
public class SentenceRecognitionModel extends AbstractModel {

    /**
     * 腾讯云项目 ID，可填 0，总长度不超过 1024 字节。
     */
    private Long ProjectId;

    /**
     * 子服务类型。2： 一句话识别。
     */
    private Long SubServiceType;

    /**
     * 引擎模型类型。
     * 电话场景：
     * • 8k_en：电话 8k 英语；
     * • 8k_zh：电话 8k 中文普通话通用；
     * 非电话场景：
     * • 16k_zh：16k 中文普通话通用；
     * • 16k_en：16k 英语；
     * • 16k_ca：16k 粤语；
     * • 16k_ja：16k 日语；
     * •16k_wuu-SH：16k 上海话方言；
     * •16k_zh_medical：16k 医疗。
     */
    private String EngSerViceType;

    /**
     * 语音数据来源。0：语音 URL；1：语音数据（post body）。
     */
    private Long SourceType;

    /**
     * 识别音频的音频格式。mp3、wav。
     */
    private String VoiceFormat;

    /**
     * 用户端对此任务的唯一标识，用户自助生成，用于用户查找识别结果。
     */
    private String UsrAudioKey;

    /**
     * 语音 URL，公网可下载。当 SourceType 值为 0（语音 URL上传） 时须填写该字段，为 1 时不填；URL 的长度大于 0，小于 2048，需进行urlencode编码。音频时间长度要小于60s。
     */
    private String Url;

    /**
     * base64的语音数据
     */
    private String Data;

    /**
     * 数据长度，单位为字节。当 SourceType 值为1（本地语音数据上传）时必须填写，当 SourceType 值为0（语音 URL上传）可不写（此数据长度为数据未进行base64编码时的数据长度）。
     */
    private Long DataLen;

    /**
     * 热词id。用于调用对应的热词表，如果在调用语音识别服务时，不进行单独的热词id设置，自动生效默认热词；如果进行了单独的热词id设置，那么将生效单独设置的热词id。
     */
    private String HotwordId;

    /**
     * 是否过滤脏词（目前支持中文普通话引擎）。0：不过滤脏词；1：过滤脏词；2：将脏词替换为 * 。默认值为 0。
     */
    private Long FilterDirty;

    /**
     * 是否过语气词（目前支持中文普通话引擎）。0：不过滤语气词；1：部分过滤；2：严格过滤 。默认值为 0。
     */
    private Long FilterModal;

    /**
     * 是否过滤标点符号（目前支持中文普通话引擎）。 0：不过滤，1：过滤句末标点，2：过滤所有标点。默认值为 0。
     */
    private Long FilterPunc;

    /**
     * 是否进行阿拉伯数字智能转换。0：不转换，直接输出中文数字，1：根据场景智能转换为阿拉伯数字。默认值为1。
     */
    private Long ConvertNumMode;

    /**
     * 是否显示词级别时间戳。0：不显示；1：显示，不包含标点时间戳，2：显示，包含标点时间戳。支持引擎8k_zh，16k_zh，16k_en，16k_ca，16k_ja，16k_wuu-SH。默认值为 0。
     */
    private Long WordInfo;

    @Override
    protected void toMap(HashMap<String, String> map, String prefix) {
        this.setParamSimple(map, prefix + "ProjectId", this.ProjectId);
        this.setParamSimple(map, prefix + "SubServiceType", this.SubServiceType);
        this.setParamSimple(map, prefix + "EngSerViceType", this.EngSerViceType);
        this.setParamSimple(map, prefix + "SourceType", this.SourceType);
        this.setParamSimple(map, prefix + "VoiceFormat", this.VoiceFormat);
        this.setParamSimple(map, prefix + "UsrAudioKey", this.UsrAudioKey);
        this.setParamSimple(map, prefix + "Url", this.Url);
        this.setParamSimple(map, prefix + "Data", this.Data);
        this.setParamSimple(map, prefix + "DataLen", this.DataLen);
        this.setParamSimple(map, prefix + "HotwordId", this.HotwordId);
        this.setParamSimple(map, prefix + "FilterDirty", this.FilterDirty);
        this.setParamSimple(map, prefix + "FilterModal", this.FilterModal);
        this.setParamSimple(map, prefix + "FilterPunc", this.FilterPunc);
        this.setParamSimple(map, prefix + "ConvertNumMode", this.ConvertNumMode);
        this.setParamSimple(map, prefix + "WordInfo", this.WordInfo);
    }
}
