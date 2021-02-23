
const userId = "6034fc36c6033033bd603e84"

class UItools {

    generateHeader() {
        const header = document.createElement("div");
              header.classList.add("header");
        const img = document.createElement("img");
              img.src = "images/Zeiss-logo.jpg";

        header.appendChild(img);
        return header;
    }

    generateFooter() {
        const footer = document.createElement("div");
            footer.classList.add("footer");
       
            const footerHTML = `
                        <hr>
                        <div class="datas">
                            <div>Carl Zeiss Digital Innovation AG</div>
                            <div>Profil István Gerliczky</div>
                            <div></div>
                        </div>
                        `;
        footer.innerHTML = footerHTML;

        return footer;
    }

    generateProfilSite(userData) {
        const profilSite = document.querySelector("#profilSite");

        document.querySelector("#profilSite .name").innerText = userData.name;
        document.querySelector("#profilSite .role").innerText = userData.role;
        document.querySelector("#profilSite .position").innerText = userData.position;
        document.querySelector("#profilSite .strengths").innerText = userData.strengths.toString();
        document.querySelector("#profilSite .studies").innerText = userData.studium + " , " + userData.studiumYear;

        if(userData.sector) {
            document.querySelector("#profilSite .datas .sector").innerText = "Branchen";
            document.querySelector("#profilSite .datas .sectors").innerText = userData.sector;
        }

       /* if(userData.sectors.length != 0) {

            document.querySelector("#profilSite .datas .sector").innerText = "Branchen";
            const sectores = document.querySelector("#profilSite .datas .sectores");

            for(let i = 0; i < userData.sectors.length; i++) {
                let p = document.createElement("p");
                let li = document.createElement("li");
                    p.innerText = userData.sectors[i].name;
                    li.innerText = userData.sectors[i].sector.toString();

                    sectores.appendChild(p);
                    sectores.appendChild(li);
            }
            
        }*/

        if(userData.certificates.length != 0) {
            document.querySelector("#profilSite .certificate h2").innerText = "Zertifizierungen";
            document.querySelector("#profilSite .certificate .certificates").innerText = userData.certificates.toString();
        }
        
        const footer = this.generateFooter();
        profilSite.appendChild(footer);

    }

    generateProjectDiv(json) {
        const projektdiv = document.createElement("div");
                projektdiv.classList.add("projekt");

            let projektHTML = `
                <table>
                    <thead>
                        <tr>
                            <th>${json.startTime} - ${json.endTime}</th>
                            <th>${json.workplace}</th>
                            <th>
                                <button type="button" data-toggle="modal" data-target="#projectModal"
                                    class="edit-project btn btn-secondary" id="editProject-${json.id}"> Edit </button>

                                <button type="button" class="delete-project btn btn-danger" id="deleteProject-${json.id}"> Delete </button>
                            </th>
                            
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td></td>
                            <td>
                                <p>${json.name}</p>
                                <p>${json.post}</p>
                            </td>
                        </tr>
                    </tbody>
                </table>               
            <div>
                <div class="description_section">
                    <p>${json.description}</p>
                    <div class="activities"><u>Tätigkeiten:</u> 
                        <ul>`
                            let li = ``;
                            for (let k = 0; k < json.activities.length; k++) {

                                li += `<li>${json.activities[k]}</li>`;
                            }
            projektHTML += li;
            projektHTML += `</ul>
                    </div>
                    <div class="tools">Tools: 
                        <span>${json.tools}</span>
                    </div>
                </div>                        
        `;


            projektdiv.innerHTML = projektHTML;

            return projektdiv;
    }

    generateNewSite() {
        const newSite = document.createElement("div");
            newSite.classList.add("site");
            newSite.style.pageBreakBefore = "always";
            

        const header = this.generateHeader();
        const footer = this.generateFooter();

            newSite.appendChild(header);
            newSite.appendChild(footer);

        return newSite;
    }

    generateProjectSection(json) {
        const siteSection = document.querySelector(".site_section");
        const maxHeight = 600;

        let needHeaderForFirstSide = true;
        let siteCount = 1;
        
        for (let i = 0; i < json.length; i++) {

            let actualSite = document.getElementById('knowledgeSite-' + siteCount);

            if(siteCount == 1 && needHeaderForFirstSide) {
                const header = this.generateHeader();
                const footer = this.generateFooter();
                actualSite.appendChild(header);
                actualSite.appendChild(footer);


                needHeaderForFirstSide = false;
            } 

            const projektdiv = this.generateProjectDiv(json[i]);
            actualSite.appendChild(projektdiv);

            if((actualSite.querySelectorAll('.projekt').length) == 2) {

                console.log("Site "+siteCount + "-----------");
                console.log(actualSite.lastChild.previousSibling.clientHeight + " "+ actualSite.lastChild.clientHeight);
                console.log(actualSite.lastChild.previousSibling.clientHeight + actualSite.lastChild.clientHeight + " px");             

                /* Test for json data processing instead of DOM element height, because it will not succeed due to responsiveness  */
                /*console.log("Description text length prev-child : " + actualSite.lastChild.previousSibling.querySelector(".description_section > p").innerText.length);
                console.log("Description text length last-child " +actualSite.lastChild.querySelector(".description_section > p").innerText.length);*/

                const actualHeight = actualSite.lastChild.previousSibling.clientHeight + actualSite.lastChild.clientHeight;

                if(i < json.length - 1) {

                    if(actualHeight > maxHeight) {
                        actualSite.removeChild(actualSite.lastChild);
                        siteCount++;
                        const newSite = this.generateNewSite();
                              newSite.id = "knowledgeSite-" + siteCount;
                        const projektdiv = this.generateProjectDiv(json[i]);
    
                        newSite.appendChild(projektdiv);
                        siteSection.appendChild(newSite);
    
                    } else {
    
                        siteCount++;
                        const newSite = this.generateNewSite();
                              newSite.id = "knowledgeSite-" + siteCount;
                              
                        siteSection.appendChild(newSite);
                    }
                }
                                             
            }

            if(i == json.length - 1) {
                this.setFooterSiteNum();
            }

            document.querySelector("#editProject-" + json[i].id).addEventListener("click", () => editProject(json[i]));
            document.querySelector("#deleteProject-" + json[i].id).addEventListener("click", () => deleteProject(json[i].id));
        }      
    }

    setFooterSiteNum() {
        const maxSiteNum = document.getElementById("sites").querySelectorAll(".site").length;

        [...document.getElementById("sites").querySelectorAll(".site")].map((site, index) => {
            site.querySelector(".footer .datas > div:last-child").innerText = `Seite ${index + 1} von ${maxSiteNum}`;
        });
    }      
}



class formTools {
    

    static async populateProjectForm() {
        const projects = await ProcessData.getFromServer("http://localhost:8080/project/getAllProjectFromList");
        const properties = await ProcessData.getFromServer("http://localhost:8080/personal/getPersonalProperties");
        console.log(projects);
        const select = document.getElementById("projectSelector");
        

        projects.forEach(element => {
            const optionForProject = document.createElement("option");
            optionForProject.innerText = element.workplace;
            optionForProject.value = element.workplace;
            
            select.appendChild(optionForProject);
           
        });

        select.addEventListener("change", (event) => {
            const actualProject = projects.find((project) => project.workplace === event.target.value);
            console.log("ActialProject");
            console.log(actualProject);
            
            document.getElementById("activities").innerHTML = null;
            document.getElementById("tools").innerHTML = null;
            document.querySelector("#projectForm #description").innerHTML = null;

            const projectName = document.getElementById("projectNameSelector");
            recreateNode(projectName, true);

            this.populateProjectName(actualProject.costumer, properties);
            
        });
    }

    static populateProjectName(actualProjectCustomer, properties) {
        const projectName = document.getElementById("projectNameSelector");
        projectName.innerHTML = '<option value="" selected disabled hidden>Choose Name</option>';

            actualProjectCustomer.forEach((customer) => {
                const optionForCustomerName = document.createElement("option");
                optionForCustomerName.innerText = customer.name;
                optionForCustomerName.value = customer.name;
                
                projectName.appendChild(optionForCustomerName);
            });


            projectName.addEventListener("change", (event) => {
                
                const customer = actualProjectCustomer.find((custom) => custom.name === event.target.value);
               console.log(customer);
                this.populateDescription(customer.project);
                this.populateMultiselects(properties);
                
            });
    }

    static async populateMultiselects(properties) {
        
        const selectActivities = document.getElementById("activities");
        const selectTools = document.getElementById("tools");

        selectTools.innerHTML = null;
        selectActivities.innerHTML = null;

       
        
        properties[0].tools.forEach((tools) => {
                const optionTools = document.createElement("option");
                    optionTools.innerText = tools;
                    optionTools.value = tools;
                
                selectTools.appendChild(optionTools);
            });

        properties[0].activities.forEach((activities) => {
                const optionTools = document.createElement("option");
                    optionTools.innerText = activities;
                    optionTools.value = activities;
                
                selectActivities.appendChild(optionTools);
            });
        
    }

    static populateDescription(actualProjectCustomer) {
        const descriptionField = document.querySelector("#projectForm #description");
        descriptionField.innerHTML = null;
        actualProjectCustomer.forEach((projectDetails) => {
            descriptionField.innerText = projectDetails.description;
        });
    }

    static populatePersonalForm(userData) {
        const personalForm = document.querySelector("#personalForm");

        [...personalForm.querySelectorAll("input")].map((input) => {
             input.value = userData[input.id];
        });
    }


}

class ProcessData {


    static async getFromServer(url) {
        const response = await fetch(url, {
            method: 'GET',
        });

        if (!response.ok) {
            throw new Error(`An error has occured: ${response.status}`);
        }

        const jsonData = await response.json();
        return jsonData;
    }

    static async postToServer(url, data) {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
              },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            throw new Error(`An error has occured: ${response.status}`);
        }

        const jsonData = await response.json();
        return jsonData;
    }
    

    static async readJsonFile(file) {
        const response = await fetch(file);

        if (!response.ok) {
            throw new Error(`An error has occured: ${response.status}`);
        }

        const jsonData = await response.json();
        return jsonData
    }
}

function submitPersonalForm() {
    let finalPersonalObj = {
        name: "",
        role: "",
        position: "",
        year_of_birth: "",
        strengths: "",
        studium: "",
        studiumYear: "",
        sector: "",
        certificates: ""
    };

    const personalForm = document.querySelector("#personalForm");

        [...personalForm.querySelectorAll("input")].map((input) => {
             finalPersonalObj[input.id] = input.value;

             if(input.id == "strengths") finalPersonalObj.strengths = input.value.split(",");
             if(input.id == "certificates") finalPersonalObj.certificates = input.value.split(",");
        });

        
        const url = "http://localhost:8080/user/modifyUserPersonal/" + userId;

        ProcessData.postToServer(url, finalPersonalObj).then(res => {
            alert(res.message);
            location.reload();
        });

}

function editProject(json) {
    const modal = document.getElementById("projectModal");
        modal.querySelector(".modal-title").innerText = "Editing.... " +json.workplace+ " project";
        modal.querySelector("#description").value = json.description;

        [...modal.querySelectorAll("input")].map((input) => {
            if(input.id == "activities") input.value = json[input.id].toString();
            if(input.id == "tools") input.value = json[input.id].toString();

            input.value = json[input.id];
        });

}

function saveProjectChanges() {
    const modal = document.getElementById("projectModal");

    let finalProjectObj = {
        id: "",
        workplace: "",
        startTime: "",
        endTime: "",
        name: "",
        post: "",
        activities: "",
        tools: "",
        description: ""
    };

    finalProjectObj.description = modal.querySelector("#description").value;

    [...modal.querySelectorAll("input")].map(input => {
        finalProjectObj[input.id] = input.value;

        if(input.id == "activities") finalProjectObj.activities = input.value.split(",");       
        if(input.id == "tools") finalProjectObj.tools = input.value.split(",");       
    });
    
    //const id = modal.querySelector("#id").value;

    const url = "http://localhost:8080/user/modifyProject/" + userId;


    ProcessData.postToServer(url, finalProjectObj).then(res => {
        alert(res.message);
        location.reload();
    });

}

function deleteProject(id) {

    if(confirm("Are you sure to delete this Project?")){

        const url = "http://localhost:8080/user/deleteProject/" + userId + "/" + id;
        console.log(url);

        ProcessData.getFromServer(url).then(res => {
            alert(res.message);
            location.reload();
        });

    }
     
}

function recreateNode(el, withChildren) {
    if (withChildren) {
      el.parentNode.replaceChild(el.cloneNode(true), el);
    }
    else {
      var newEl = el.cloneNode(false);
      while (el.hasChildNodes()) newEl.appendChild(el.firstChild);
      el.parentNode.replaceChild(newEl, el);
    }
}

function submitProjectForm() {

    const toolbarForm = document.querySelector(".toolbar #projectForm");

    const projectSelector = toolbarForm.querySelector("#projectSelector").value;
    const projectNameSelector = toolbarForm.querySelector("#projectNameSelector").value;
    const projectStart = toolbarForm.querySelector("#projectStart").value.replace("-", "/");
    const projectEnd = toolbarForm.querySelector("#projectEnd").value.replace("-", "/");
    const description = toolbarForm.querySelector("#description").value;
    const activities = toolbarForm.querySelectorAll('#activities :checked');
    const tools =  toolbarForm.querySelectorAll('#tools :checked');

    const activitiesList = [...activities].map(option => option.value);
    const toolsList = [...tools].map(option => option.value);


    let finalProjectObj = {
        workplace: projectSelector,
        startTime: projectStart,
        endTime: projectEnd,
        name: projectNameSelector,
        post: "",
        activities: activitiesList,
        tools: toolsList,
        description: description
    }
    

    const url = "http://localhost:8080/user/addProjectToUser/" + userId;

    ProcessData.postToServer(url, finalProjectObj).then(res => {
        alert(res.message);
        location.reload();
    });
}


function convertToPdf() {

    const html = document.getElementById("sites");

    const options = {
        filename: 'CV.pdf',
        image: {
            type: 'jpeg',
            quality: 0.98
        },
        html2canvas: {
            scale: 4,
            dpi: 300,
            letterRendering: true,
            useCORS: true
        },
        jsPDF: {
            unit: 'in',
            format: 'a4',
            orientation: 'portrait'
        }
    }

    html2pdf().from(html).set(options).save();

}




async function run() {


   const user = await ProcessData.getFromServer("http://localhost:8080/user/getUser/" + userId);
    console.log(user);

    const uiTools = new UItools();

    uiTools.generateProfilSite(user.personal);
    uiTools.generateProjectSection(user.projects);
    formTools.populateProjectForm();
    formTools.populatePersonalForm(user.personal);
}

run();



