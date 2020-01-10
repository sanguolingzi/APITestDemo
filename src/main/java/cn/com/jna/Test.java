/*
package cn.com.jna;


import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.HashMap;

import com.sun.jna.Library;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
import com.sun.jna.Structure;
import com.sun.jna.ptr.ByReference;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.win32.StdCallFunctionMapper;
import com.sun.jna.win32.StdCallLibrary;

public class Test {

    public interface JieXiDllInvoker extends StdCallLibrary

    { //Library {
        JieXiDllInvoker Instance  = (JieXiDllInvoker) Native.loadLibrary("JieXi1", JieXiDllInvoker.class, new HashMap<String, StdCallFunctionMapper>() {{

        }});
        //
        public boolean Eva(BSTR out_exp, BSTR local_var, BSTRByReference out_var, BSTRByReference out_var_func, BSTRByReference out_expression);
    }

    public static class BSTR extends PointerType {
        public static class ByReference extends BSTR implements Structure.ByReference { }

        public BSTR() {
            super(Pointer.NULL);
        }

        public BSTR(Pointer pointer) {
            super(pointer);
        }

        public BSTR(String value) {
            super();
            this.setValue(value);
        }

        public void setValue(String value) {
            if(value == null) {
                value = "";
            }
            try {
                byte[] encodedValue = value.getBytes("UTF-16LE");
                // 4 bytes for the length prefix, length for the encoded data,
                // 2 bytes for the two NULL terminators
                Memory mem = new Memory(4 + encodedValue.length + 2);
                mem.clear();
                mem.setInt(0, encodedValue.length);
                mem.write(4, encodedValue, 0, encodedValue.length);
                this.setPointer(mem.share(4));
            } catch (UnsupportedEncodingException ex) {
                throw new RuntimeException("UTF-16LE charset is not supported", ex);
            }
        }

        public String getValue() {
            try {
                Pointer pointer = this.getPointer();
                if(pointer == null) {
                    return "";
                }
                int stringLength = pointer.getInt(-4);
                return new String(pointer.getByteArray(0, stringLength), "UTF-16LE");
            } catch (UnsupportedEncodingException ex) {
                throw new RuntimeException("UTF-16LE charset is not supported", ex);
            }
        }

        @Override
        public String toString() {
            return this.getValue();
        }
    }

    public static class BSTRByReference extends ByReference {
        public BSTRByReference() {
            super(Integer.SIZE);
        }

        public BSTRByReference(BSTR value) {
            this();
            setValue(value);
        }

        public void setValue(BSTR value) {
            this.getPointer().setPointer(0, value.getPointer());
        }

        public BSTR getValue() {
            return new BSTR(getPointer().getPointer(0));
        }

        public String getString() {
            return this.getValue().getValue();
        }
    }

    */
/*CompilerDll.dll 调用*//*

    public interface CompilerDllInvoker extends com.sun.jna.win32.StdCallLibrary { //Library {
        CompilerDllInvoker Instance  = (CompilerDllInvoker)Native.loadLibrary("CompilerDll", CompilerDllInvoker.class, new HashMap<String, StdCallFunctionMapper>() {{
            put(Library.OPTION_FUNCTION_MAPPER, new StdCallFunctionMapper() {
                @Override
                public String getFunctionName(NativeLibrary library, Method method) {
                    String methodName = method.getName();
                    if (methodName.equals("PROGRAM")) {
                        return "_PROGRAM@20";
                    } else if(methodName.equals("CALCULATE")) {
                        return "_CALCULATE@16";
                    } else if(methodName.equals("RealseRec")) {
                        return "_RealseRec@4";
                    }
                    // others
                    return super.getFunctionName(library, method);
                }
            });
        }});

        public int PROGRAM(BSTR outvar, BSTR localvar, BSTR code, BSTRByReference tag, BSTRByReference err);

        public int CALCULATE(BSTR tag, BSTR ex, DoubleByReference ret, BSTRByReference err);

        public int RealseRec(BSTR tag);
    }

    public static boolean Eva(String out_exp, String local_var, StringBuilder out_var, StringBuilder out_var_func, StringBuilder out_expression) {
        BSTRByReference b_out_var =  new BSTRByReference();
        BSTRByReference b_out_var_func = new BSTRByReference();
        BSTRByReference b_out_expression = new BSTRByReference();
        boolean result = JieXiDllInvoker.Instance.Eva(new BSTR(out_exp), new BSTR(local_var),
                b_out_var, b_out_var_func, b_out_expression);
        if (out_var != null) {
            out_var.append(b_out_var.getString());
        }
        if (out_var_func != null) {
            out_var_func.append(b_out_var_func.getString());
        }
        if (out_expression != null) {
            out_expression.append(b_out_expression.getString());
        }
        return result;
    }

    public static int PROGRAM(String outvar, String localvar, String code, StringBuilder tag, StringBuilder err) {
        BSTRByReference b_tag =  new BSTRByReference();
        BSTRByReference b_err = new BSTRByReference();
        int result = CompilerDllInvoker.Instance.PROGRAM(new BSTR(outvar), new BSTR(localvar), new BSTR(code), b_tag, b_err);
        if (tag != null) {
            tag.append(b_tag.getString());
        }
        if (err != null) {
            err.append(b_err.getString());
        }
        return result;
    }

    public static class DoubleRef {
        private double _result;
        public void setResult(double result) {
            _result = result;
        }
        public double getResult() {
            return _result;
        }
    }
    public static int CALCULATE(String tag, String ex, DoubleRef ret, StringBuilder err) {
        DoubleByReference dbr = new DoubleByReference();
        BSTRByReference b_err = new BSTRByReference();
        int result = CompilerDllInvoker.Instance.CALCULATE(new BSTR(tag), new BSTR(ex), dbr, b_err);
        if (err != null) {
            err.append(b_err.getString());
        }
        if (ret != null) {
            ret.setResult(dbr.getValue());
        }
        return result;
    }

    public static int RealseRec(String tag) {
        return CompilerDllInvoker.Instance.RealseRec(new BSTR(tag));
    }

    public static void TestMain(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        System.out.println(System.getProperty("java.library.path"));

        System.out.println(Test.Eva("aa","bb",sb,sb1,sb2));
        System.out.println(sb2.toString());
    }
}
*/
