
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

    generateProjectDiv(json) {
        const projektdiv = document.createElement("div");
                projektdiv.classList.add("projekt");

            let projektHTML = `
                <table>
                    <thead>
                        <tr>
                            <th>${json.time}</th>
                            <th>${json.workplace}</th>
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
                console.log("Description text length prev-child : " + actualSite.lastChild.previousSibling.querySelector(".description_section > p").innerText.length);
                console.log("Description text length last-child " +actualSite.lastChild.querySelector(".description_section > p").innerText.length);

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
        }      
    }

    setFooterSiteNum() {
        const maxSiteNum = document.getElementById("sites").querySelectorAll(".site").length;
        /*const siteNum =  document.getElementById("sites").querySelectorAll(".site");
        
        siteNum.forEach(site => {
            const currentText = site.querySelector(".footer .datas > div:last-child").innerText;
            const updatedText = currentText + " " +maxSiteNum;
            site.querySelector(".footer .datas > div:last-child").innerText = updatedText;            
        });*/

        [...document.getElementById("sites").querySelectorAll(".site")].map((site, index) => {
            site.querySelector(".footer .datas > div:last-child").innerText = `Seite ${index + 1} von ${maxSiteNum}`;
        });
    }      
}

class formTools {

    static async populateProjectForm() {
        const projects = await ProcessData.readJsonFromServer("http://localhost:8080/project/getAllProjectFromList");
        console.log(projects);
        let select = document.getElementById("projectSelector");
        

        projects.forEach(element => {
            let optionForProject = document.createElement("option");
            optionForProject.innerText = element.workplace;
            optionForProject.value = element.workplace;
            
            select.appendChild(optionForProject);
           
        });

        select.addEventListener("change", (event) => {
            let actualProject = projects.find((project) => project.workplace == event.target.value);
            console.log("ActialProject");
            console.log(actualProject);
            
            this.populateProjectName(actualProject.costumer);

        });
    }

    static populateProjectName(actualProjectCustomer) {
        let projectName = document.getElementById("projectNameSelector");
        projectName.innerHTML = null;
                       

            actualProjectCustomer.forEach((customer) => {
                let optionForCustomerName = document.createElement("option");
                optionForCustomerName.innerText = customer.name;
                optionForCustomerName.value = customer.name;
                
                projectName.appendChild(optionForCustomerName);
            });

            projectName.addEventListener("change", (event) => {
                let customer = actualProjectCustomer.find((customer) => customer.name == event.target.value);
                console.log(customer);
                this.populateDescription(customer);
                this.populateMultiselects();
            });
    }

    static async populateMultiselects() {
        const properties = await ProcessData.readJsonFromServer("http://localhost:8080/project/getProperties");
        const selectActivities = document.getElementById("activities");
        const selectTools = document.getElementById("tools");

        selectTools.innerHTML = null;
        selectActivities.innerHTML = null;

       
        properties.forEach((data) => {
            data.tools.forEach((tools) => {
                let optionTools = document.createElement("option");
                    optionTools.innerText = tools;
                    optionTools.value = tools;
                
                selectTools.appendChild(optionTools);
            });

            data.activities.forEach((activities) => {
                let optionTools = document.createElement("option");
                    optionTools.innerText = activities;
                    optionTools.value = activities;
                
                selectActivities.appendChild(optionTools);
            });
        });
    }

    static populateDescription(actualProjectCustomer) {
        let descriptionField = document.querySelector("#projectForm #description");
        descriptionField.innerText = "";
        actualProjectCustomer.project.forEach((projectDetails) => {
            descriptionField.innerText = projectDetails.description;
        });
    }


}



class ProcessData {


    static async readJsonFromServer(url) {
        const response = await fetch(url, {
            method: 'GET',
        });

        if (!response.ok) {
            throw new Error(`An error has occured: ${response.status}`);
        }

        const jsonData = await response.json();
        return jsonData;
    }

    static async addProject(url, data) {
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

function submitProjectForm() {
    const projectSelector = document.getElementById("projectSelector").value;
    const projectNameSelector = document.getElementById("projectNameSelector").value;
    const projectStart = document.getElementById("projectStart").value;
    const projectEnd = document.getElementById("projectEnd").value;
    const description = document.getElementById("description").value;
    const activities = document.querySelectorAll('#activities :checked');
    const tools =  document.querySelectorAll('#tools :checked');

    const activitiesList = [...activities].map(option => option.value);
    const toolsList = [...tools].map(option => option.value);

    const fillTime = projectStart + " - " +projectEnd;

    let finalProjectObj = {
        workplace: projectSelector,
        time: fillTime,
        name: projectNameSelector,
        post: "",
        activities: activitiesList,
        tools: toolsList,
        description: description
    }

    const url = "http://localhost:8080/project/addProject";

    ProcessData.addProject(url, finalProjectObj).then(res => {
        alert(res.message);
        location.reload();
    });
}


async function run() {


    const projectsFromServer = await ProcessData.readJsonFromServer("http://localhost:8080/project/getProjects");
    console.log(projectsFromServer);

    const uiTools = new UItools();

    uiTools.generateProjectSection(projectsFromServer);
    formTools.populateProjectForm();
}

run();



