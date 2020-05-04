package com.ruoyi.common.utils.file;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileUploadUtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    private final String testStr = "测试this关键字使用";

    @Test
    public void testThisStr() {
        new FileUploadUtils().testThisStr();
    }
}