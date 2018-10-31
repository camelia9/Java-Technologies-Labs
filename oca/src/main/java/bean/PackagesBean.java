package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "packagesBean",eager = true)
@SessionScoped
public class PackagesBean implements Serializable {
}
