package annotaionconfig.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

public class DateConverter implements Converter<String, Date> {
    private Logger logger = LoggerFactory.getLogger(DateConverter.class);
    private static final String pattern = "yyyy-MM-dd";
    private static SimpleDateFormat sdf = new SimpleDateFormat(pattern);

    @Override
    @Nullable
    public Date convert(String source) {
        logger.info("前端页面传递过来的时间为：" + source);
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            throw new IllegalArgumentException("无效的日期格式，参考：" + pattern);
        }
    }

}