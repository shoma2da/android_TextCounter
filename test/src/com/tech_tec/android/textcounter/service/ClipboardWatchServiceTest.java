package com.tech_tec.android.textcounter.service;

import android.test.AndroidTestCase;

public class ClipboardWatchServiceTest extends AndroidTestCase {
    
    public void testBindはできない() {
        ClipboardWatchService service = new ClipboardWatchService();
        assertNull(service.onBind(null));
    }
    
}
