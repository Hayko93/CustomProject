package am.client.market.servlets.util.validator;

import org.apache.commons.fileupload.FileItem;

import java.util.List;

public class RequestValidator<T> {
    private T value;

    List<FileItem> fileItemList;
    private boolean hasError;


    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public void setValue(T value){
        this.value = value;
    }

    public List<FileItem> getFileItemList(){
        return this.fileItemList;
    }
    public void setFileItemList(List<FileItem> fileItems){
        this.fileItemList = fileItems;
    }

    public T getValue(){
        return this.value;
    }
}
