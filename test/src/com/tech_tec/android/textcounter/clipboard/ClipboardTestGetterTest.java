package com.tech_tec.android.textcounter.clipboard;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipboardManager;
import android.content.Context;
import android.test.AndroidTestCase;

public class ClipboardTestGetterTest extends AndroidTestCase {
    
    public void testクリップボードの文字列を取得できる() {
        ClipData mockClipData = mock(ClipData.class);
        Item mockItem = mock(Item.class);
        when(mockClipData.getItemAt(0)).thenReturn(mockItem);
        when(mockItem.getText()).thenReturn("text");
        
        ClipboardManager mockClipboardManager = mock(ClipboardManager.class);
        when(mockClipboardManager.hasPrimaryClip()).thenReturn(true);
        when(mockClipboardManager.getPrimaryClip()).thenReturn(mockClipData);
        Context mockContext = mock(Context.class);
        when(mockContext.getSystemService(Context.CLIPBOARD_SERVICE)).thenReturn(mockClipboardManager);
        
        
        ClipboardTextGetter getter = new ClipboardTextGetter(mockContext);
        assertEquals("text", getter.getText());
    }
    
    public void test文字列がコピーされていない場合はnullが取得できる() {
        ClipboardManager mockClipboardManager = mock(ClipboardManager.class);
        when(mockClipboardManager.hasPrimaryClip()).thenReturn(false);
        Context mockContext = mock(Context.class);
        when(mockContext.getSystemService(Context.CLIPBOARD_SERVICE)).thenReturn(mockClipboardManager);
        
        ClipboardTextGetter getter = new ClipboardTextGetter(mockContext);
        assertNull(getter.getText());
    }
    
    public void test文字数が取得できる() {
        ClipboardTextGetter nullGetter = new ClipboardTextGetter(mock(Context.class)) {
            @Override
            public String getText() {
                return null;
            }
        };
        assertEquals(0, nullGetter.getTextLength());
        
        ClipboardTextGetter hogehogeGetter = new ClipboardTextGetter(mock(Context.class)) {
            public String getText() {
                return "hogehoge";
            }
        };
        assertEquals(8, hogehogeGetter.getTextLength());
    }
    
}
