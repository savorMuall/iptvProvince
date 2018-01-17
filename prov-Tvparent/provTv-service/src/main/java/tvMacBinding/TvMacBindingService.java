package tvMacBinding;

import com.iptv.model.tvMacBinding.entity.TvMacBinding;

public interface TvMacBindingService {


    int deleteByPrimaryKey(Integer id);

    int insert(TvMacBinding record);

    int insertSelective(TvMacBinding record);

    TvMacBinding selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TvMacBinding record);

    int updateByPrimaryKey(TvMacBinding record);

    /**
     * 根据mac  账号  省份id查询是否有绑定记录
     * @param mac
     * @param accountNumber
     * @return
     */
    TvMacBinding selectByMacIpProvId(final String mac,final String accountNumber,final Integer provId,final byte state);

    /**
     * 查询当前省份回滚的编号 最旧的一个
     * @param provId
     * @return
     */
    TvMacBinding selectByProvIdState(final Integer provId);

    /**
     * 查询当前省份已经使用的最后的一个号码（包括生效的 和暂存的）
     * @param provId
     * @return
     */
    TvMacBinding selectByProvIdStateUnUsed(final Integer provId);

    /**
     * 查询当前批次的编码关联的旧的mac信息
     * @param provId
     * @param batchNumber
     * @param number
     * @return
     */
    TvMacBinding selectOldByProvIdStateNumber(final Integer provId,
                                              final Integer batchNumber,
                                              final Integer number);



}
