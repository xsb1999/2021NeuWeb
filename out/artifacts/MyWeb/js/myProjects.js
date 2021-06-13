function Project(title, summary, description, type, language){
    this.title = title;
    this.summary = summary;
    this.description = description;
    this.type = type;
    this.language = language;
}

var p1 = new Project('RRT','Three types of RRT algorithm','RRT is a path planning method using in autonomous driving based on sampling.It can quickly search the entire state space (the real world) and is biased towards the unexplored space. It is very similar to trees with lots of limbs stretching out at each time.The main working flow is that we start from the start position and protrude some limbs randomly, once the limb of the tree reaches the destination, the path is found successfully.','algorithm','Python');
var p2 = new Project('HIS','The online hospital system of NEU','It is an online hospital system of NEU, including six functions (registration, return, visit, prescription, delivery, payment).','development','Java');
var p3 = new Project('APDP','The data processing of the environment data','The mechine detecting the concentration of CO2 in the air will generate lots of raw data, I need to process them, deleting those bad data, calculating many values like the average.','data processing','Python');
var p4 = new Project('PSO','My improvement of PSO algorithm','I changed some parameters of the function in the original PSO algorithm, like the weight, the number of particles. By the way, the speed of the algorithm is slightly improved after my modification.','algorithm','Python');

var myProjects = [p1,p2,p3,p4];
