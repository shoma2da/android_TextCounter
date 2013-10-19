package com.tech_tec.android.textcounter.service;

import android.app.Service;
import android.test.AndroidTestCase;

public class ClipboardWatchServiceTest extends AndroidTestCase {
    
    public void testBindはできない() {
        ClipboardWatchService service = new ClipboardWatchService();
        assertNull(service.onBind(null));
    }
    
    public void testStartCommandの返り値はSTICKY() {
        ClipboardWatchService service = new ClipboardWatchService();
        assertEquals(Service.START_STICKY, service.onStartCommand(null, 0, 0));
    }
    
}
