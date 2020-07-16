package cn.zhangyuying.analysis.imexport;

import cn.zhangyuying.analysis.common.CommonConst;
import cn.zhangyuying.analysis.common.CommonUtil;
import cn.zhangyuying.analysis.dao.RecordDao;
import cn.zhangyuying.analysis.record.bean.Record;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zyy.
 */
@Service
public class ImexportService {
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(ImexportService.class);
    private final RecordDao recordDao;

    public ImexportService(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    void importExcelOrganData(MultipartFile excelFile) throws IOException {
        // 从excel中获取数据，拼装成records
        List<Record> records = buildRecordsFromExcel(excelFile);
        // 入库
        recordDao.insertRecords(records);
    }


    private List<Record> buildRecordsFromExcel(MultipartFile excelFile) throws IOException{
        InputStream inputStream = excelFile.getInputStream();
        // 创建工作簿
        XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
        //获取元数据
        XSSFSheet sheet = workBook.getSheet("汇总");
        // 获得行数
        int rows = sheet.getPhysicalNumberOfRows();
        logger.info("导入的数据数量：{}", rows);
        if (rows <= 2) {
            return new ArrayList<>();
        }
        List<Record> corpList = new ArrayList<>();
        Row titleRow = sheet.getRow(0);
        for (int i = 1; i <= rows; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }

            int columnNum = titleRow.getPhysicalNumberOfCells();
            Map<String, Object> map = new HashMap<>(columnNum);
            for (int j = 0; j < columnNum; j++) {
                String title = getCellValue(titleRow.getCell(j));
                if (StringUtils.isNotBlank(title)) {
                    map.put(title, getCellValue(row.getCell(j)));
                }
            }
            Record record = new Record();
            record.setId(CommonUtil.getUuid());
            record.setRecordData(map);
            record.setUpdateTime(new Date());
            corpList.add(record);
        }
        return corpList;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null || StringUtils.isBlank(cell.toString())) {
            return null;
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue().trim();
            case Cell.CELL_TYPE_FORMULA:
                cell.setCellType(Cell.CELL_TYPE_STRING);
                return cell.getStringCellValue().trim();
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    return DateFormatUtils.format(cell.getDateCellValue(), "yyyy-MM-dd HH:mm:ss");
                }
                NumberFormat nf = NumberFormat.getInstance();
                String s = nf.format(cell.getNumericCellValue());
                if (s.contains(CommonConst.COMMA)) {
                    s = s.replace(CommonConst.COMMA, CommonConst.EMPTY_STRING);
                }
                return s;
            default:
                return null;
        }


    }
}
