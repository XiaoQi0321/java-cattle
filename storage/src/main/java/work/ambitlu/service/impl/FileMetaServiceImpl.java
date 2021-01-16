package work.ambitlu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import work.ambitlu.service.FileMeta;
import work.ambitlu.service.mapper.FileMetaMapper;
import work.ambitlu.service.FileMetaService;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/16 21:46
 */
@Service
public class FileMetaServiceImpl extends ServiceImpl<FileMetaMapper, FileMeta> implements FileMetaService {
}
