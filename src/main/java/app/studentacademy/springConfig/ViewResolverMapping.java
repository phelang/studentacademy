package app.studentacademy.springConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Controller
public class ViewResolverMapping {
    @RequestMapping(method = RequestMethod.GET, value = "/") /* index.html */
    public String index(){  /* Home page WEB-INF/views/index.html */
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/footer") /* footer.html */
    public String footer(){  /* Home page WEB-INF/views/index.html */
        return "footer";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/topnavigation") /* topnavigation.html */
    public String topNavigation(){  /* Home page WEB-INF/views/index.html */
        return "topnavigation";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/about") /* about.html */
    public String about(){  /* Home page WEB-INF/views/index.html */
        return "about";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/createstudent") /* createstudent.html */
    public String createStudent(){  /* Home page WEB-INF/views/index.html */
        return "createstudent";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/managestudent") /* managestudent.html */
    public String manageStudent(){  /* Home page WEB-INF/views/index.html */
        return "managestudent";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/managestudentscore") /* managestudentscore.html */
    public String managestudentScore(){  /* Home page WEB-INF/views/index.html */
        return "managestudentscore";
    }
}
