package entities;
import java.util.ArrayList;

public class UniqueArrayList {
    private ArrayList<String> list;

    public UniqueArrayList() {
        list = new ArrayList<>();
    }

    // Thêm hoặc xóa chuỗi khỏi danh sách
    public void addOrRemove(String value) {
        if (list.contains(value)) {
            list.remove(value); // Xóa nếu đã tồn tại
        } else {
            list.add(value); // Thêm nếu chưa tồn tại
        }
    }

    // In danh sách hiện tại
    public void printList() {
        System.out.println(list);
    }
    
    public ArrayList<String> getList(){
    	return this.list;
    }

}
