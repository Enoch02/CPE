package com.cpe.util

object Constants {
    val names = listOf(
        "Adesanya Enoch",
        "Olajide Olamide",
        "Alayaki Temitope",
        "Olugbenga Samuel",
        "Bamidele-Ilo Gbolahan",
        "Bakare Daniel",
        "Ogunmodede Joshua",
        "Kolawole Olaoluwa"
    )
    val matricNumbers = listOf(
        "EES/19/20/0065",
        "EES/19/20/0420",
        "EES/19/20/0148",
        "EES/19/20/0453",
        "EES/19/20/0205",
        "EES/19/20/0197",
        "EES/19/20/0376",
        "EES/18/19/0303"
    )

    val levels = listOf("200", "300", "400", "500")

    val courses200 = listOf(
        "FEG 203" to "Engineer In Society",
        "FEG 201" to "Engineering Mathematics 1",
        "CPE 205" to "Introduction To Computer Programming",
        "MEG 207" to "Engineering Drawing 1",
        "EEG 201" to "Fundamentals Of Elect. Engineering",
        "MEG 201" to "Workshop Practice 1",
        "MEG 203" to "Engineering Thermodynamics 1",
        "MEG 213" to "Fluid Mechanics",
        "MEG 209" to "Material Science & Engineering",
        "MEG 205" to "Engineering Mechanics 1",
        "GNS 201" to "Nigerian Peoples and Culture",
        "GNS 202" to "Communication in French",
        "GNS 203" to "Entrepreneurial Studies",
        "EEG 203" to "Elect. & Elect. Engineering Lab. I",
        "FEG 202" to "Engineering Mathematics II",
        "EEG 202" to "Fundamentals of Elect. Engineering II",
        "CPE 204" to "Introduction To Computer Engineering",
        "MEG 214" to "Engineering Drawing II",
        "MEG 202" to "Workshop Practice II",
        "MEG 204" to "Strength Of Materials",
        "MEG 206" to "Engineering Mechanics II",
        "GNS 201" to "Peace Studies and Citizenship Education",
        "GNS 202" to "Introduction to Entrepreneurship Skills",
        "EEG 204" to "Elect. & Elect. Engineering Lab. II",
    )
    val courses300 = listOf(
        "FEG 301" to "Engineering Mathematics III ",
        "CPE 313" to "Computer Programming Principles ",
        "CPE 361" to "Computer Engineering Laboratories ",
        "CPE 321" to "Physical Electronics",
        "EEG 333" to "Electromagnetic Field Theory ",
        "EEG 341" to "Electric Circuit Theory I",
        "EEG 311" to "Measurement And Instrumentation",
        "CPE 302" to "Microprocessor, Architecture & Computer Language",
        "CPE 304" to "Analog Computer Basics ",
        "CPE 306" to "Digital Devices & Logic Circuits",
        "CPE 308" to "Computer Aided Designs ",
        "CPE 364" to "Digital Lab. ",
        "CPE 366" to "Microprocessor Lab.",
        "CPE 362" to "Simulation Lab. ",
        "EEG 342" to "Electric Circuit Theory II",
        "FEG 302" to "Engineering Mathematics IV",
    )
    val courses400 = listOf(
        "FEG 401" to "Technical Communications ",
        "FEG 403" to "Introduction to Entrepreneurial Studies",
        "FEG 405" to "Research Method ",
        "CPE 401" to "Digital Computer Technology ",
        "CPE 407" to "Assembly Language Programming ",
        "CPE 403" to "Embedded Systems & Interfacing Techniques",
        "CPE 461" to "Computer Engineering Lab III ",
        "EEG 431" to "Communications Principles",
        "FEG 490" to "Student (Supervised) Industrial Work Experienced Scheme (SIWES)",
    )
    val courses500 = listOf(
        "CPE 500" to "Seminar/Design Project In Computer Engineering",
        "CPE 501" to "Introduction to Software Engineering. ",
        "CPE 503" to "Digital Signal Processing  ",
        "CPE 505" to "Computer Communication Networks ",
        "CPE 507" to "Real Time Computer System ",
        "CPE 509" to "Student Project/Seminar in Computer Eng.",
        "FEG 501" to "Engineering Economics ",
        "CPE 511" to "Embedded Systems",
        "CPE 513" to "Database Systems ",
        "CPE 515" to "Mobile Communications ",
        "AEG 511" to "Agricultural Materials Handling",
        "EEG 503" to "Reliability and Maintainability of Systems ",
        "MEG 511" to "Energy Technology ",
        "CPE 502" to "Introduction to Operating Systems ",
        "CPE 504" to "Design of Digital & VLSI Systems",
        "CPE 506" to "Artificial Intelligence",
        "CPE 510" to "Engineering Project in Computer Engineering",
        "CPE 508" to "Computer Maintenance & Troubleshoot ",
        "CPE 514" to "Solid State Electronics ",
        "CPE 512" to "Distributed Systems & the Internet ",
        "AEG 506" to "Farm Transportation",
        "EEG 552" to "Industrial Electronics and Applications",
        "MEG 504" to "Automobile Engineering",
    )

    val courseDetails = mapOf(
        "CPE 204" to listOf(
            "Philosophy of sciences, History of Engineering Technology.",
            "Safety in Engineering and introduction to risk analysis.",
            "The role of Engineers in nation building. Computer Engineering as a branch of Electronics.",
            "Computer Engineering as related to other Engineering Field.",
            "Computer Engineering and Computer Science’s Similarities and Differences.",
            "Who is a Computer Engineer?",
            "Various fields of Computer Engineering."
        ),
        "CPE 313" to listOf(
            "Principle of good programming",
            "Structured programming concept",
            "Debugging and testing; string",
            "Processing internal searching and sorting,",
            "Data structure,",
            "Recursion",
        ),
        "CPE 321" to listOf(
            "Free electron motion in static electric and magnetic fields,",
            "electronic structure of matter,",
            "conductivity in crystalline solids, ",
            "theory of energy bands in conductors,",
            "insulators and semi conductors; electrons in",
            "metals and electron emissions, carriers and transport phenomena in semi-conductors,",
            "characteristics of some electron and photo devices, junction diodes, and transistors, FETs, SCR, vacuum tubes, photo",
            "resistors, diodes, transistors, photocell and light emitting diodes",
            "Elementary discrete devices fabrication techniques and IC technology.",
        ),
        "CPE 361" to listOf(
            "A laboratory work set up to illustrate topics covered in computer programming, Electronics, measurement and instrumentation.",
            "Computer Programming Lab.",
            "Electronics Lab.",
            "Measurement & Instrumentation Lab.",
        ),
        "CPE 302" to listOf(
            "Basic architecture of computer systems including fundamental concepts such as register structure,",
            "memory organization and management, organization of peripherals, and machine-level operations.",
            "These concepts are integrated through the use of assemblers, linkers and loaders.",
            "Topics include: instruction sets, symbolic addressing, bus organization, instruction fetch and execution, read/write cycles, interrupt",
            "processing, I/O processing, general microprocessor design.",
        ),
        "CPE 306" to listOf(
            "Introduction to analysis and design of digital systems.",
            "Boolean algebra and mapping methods. Karuangh and variable entered maps, combinational logic realization with gates, multiplexes, read only memories",
            "(ROMS) and programmable logic arrays (PLAS).",
            "State machine analysis and design, state diagrams, redundant state sequential counters and mainly synchronous systems, state machine realization with",
            "multiplexes, ROMs and PLAS.",
            "Asynchronous systems approach to digital systems designs, top – down design, trail and error methods. Codes, number systems and arithmetic operations.",
            "Introduction to computer structures, register transfer, hardware programming methods.",
            "Von Neumann machines and memory systems.",
            "Standard logic function with MSI circuits, seven segment display drivers, parity generators/checker encoders, adders, etc.",
        ),
        "CPE 308" to listOf(
            "Definition of computer simulation, Importance of modeling and simulation, manufacturing system management, Modeling and simulation as complementary tasks",
            "Computer simulation as an interdisciplinary tool, Examples of computer simulation application in electronics and in various fields of science and technology; PSPICE, LabVIEW MATLAB.",
            "General concepts of modeling, Continuous and discrete models and simulation.",
            "The model: components, descriptive variables and interaction rules, The concept of model state",
            "Descriptive variables: input, state, output, model parameters, state transition and output functions, Experimental frames and simplified models, Model validation, verification and credibility.",
        ),
        "CPE 362" to listOf(
            "A laboratory work set up to illustrate topics covered in microprocessor & Assembly language programming, digital devices & logic circuit, simulations and information technology.",
            "Digital Electronics I Microprocessor Lab. I & Simulation Lab.",
        ),
        "CPE 401" to listOf(
            "Review of elementary digital concepts, switching properties of electronic devices.",
            "Switching an wave-shaping circuits.",
            "Generation of non-sinusoidal waveforms; a stable, mono stable and bistable multivibrators, co-operator, Schmitt trigger and time base generators using discrete transistor, operational amplifier or other integrated circuits – Time chips and their applications.",
            "Analysis and design of logic gates of various families.",
            "Diode logic, RTL, TTL, RCL, MOS and MOS of digital integrated circuits.",
            "Interfacing between various logic families. Concepts of small, medium, large and very large scale integration and their consequences, some digital building blocks; flip flops, counters, register and decoders.",
            "Introduction to D/A and A/D conversion principles.",
        ),
        "CPE 403" to listOf(
            "Introduction to microprocessor Hardware & Embedded processor technology",
            "General Purpose processor, Single purpose processors, Application Specific Processor",
            "IC Technology",
            "Full Custom/VLSI semi-custom ASIC, PLD",
            "Error detection and correction methods in hardware communication",
            "Parity, Checksum and CRC checks Communications, Parallel Communications, flags, Interrupts and other Devices, Digital-to-Analogue Conversion, Analogue-to-Digital Conversion, Averaging, Digital Signal Processing Techniques, Filtration, Sensors of Physical Parameters.",
        ),
        "CPE 461" to listOf(
            "A laboratory work set up to illustrate topics covered in Assembly language programming, Communication principles, Microprocessor and Digital Computer Technology."
        ),
        "CPE 501" to listOf(
            "Programming methodologies, basic concepts, principles of software management document and presentation, software life-cycle",
            "software economics",
        ),
        "CPE 500" to listOf(
            """
                The main purpose of SEMINAR is to provide students with an opportunity to exercise their ability to
                present and to defend their thoughts on professional topics of their own choice. Students will be
                encouraged to devote some of their discussions to such topics as continuing professional education,
                professional societies and organization of engineering employment. Students will also be made aware of
                the role and responsibilities of Professional Engineers in society with respect to the environment, ethics,
                equity, public and worker safety and health considerations.
                A significant technical design project in Computer Engineering completed under the supervision of a
                computer engineering staff member. This design experience is based on the knowledge and skills
                acquired in earlier course work. Projects may originate from faculty members, students, or external
                sources. They may have a diverse nature and serve diverse needs. Multi-disciplinary projects are
                encouraged. Prerequisites: The student must be registered in the Computer Engineering programme or
                have permission of the Department
            """.trimIndent()
        ),
        "CPE 505" to listOf(
            "Introduction to computer Networking, protocols and Architecture, Transmission Media; transmission",
            "medium, characteristic and quality of data transmission, design of data transmission system.",
            "guided transmission media wireless transmission.",
            "Data Link Control; flow control, Error detection, error Control, frame synchronization, Addressing, Control and data, link Management. Data Transmission; concept and Terminology, Analog and digital data transmission, transmission impairment.",
            "Data Communication Interface; Asynchronous and Synchronous transmission interfacing.",
            "Data encoding; Digital Data, Digital Signals, digital data analog signals, analog data, digital signals, Analog data, analog signals, spread spectrum.",
        ),
        "CPE 507" to listOf(
            "Techniques that can be used to guarantee the completion of a computation ahead of its deadline.",
            "Scheduling techniques for periodic and non-periodic tasks.",
            "Organization and functionality of real time kernels.",
            "A Project Laboratory is integral to the course. Students must complete a sequence of two projects that involve substantial real time software design and implementation. Students work in teams. Progress is determined through a preliminary design review, presentation",
        ),
        "CPE 509" to listOf(
            "Advanced combinational and sequential logic design.",
            "Optimization of finite state machines; timing methodologies and synchronization issues.",
            "Hardware description languages (HDL): structural and behavioral descriptions, simulation and test benches, coding styles, design with HDL and FPGA implementation.",
            "Design for test: testing concepts, scan-based design and built-in self-test (BIST).",
            "Design for high speed: timing analysis, pipelining and retiming.",
            "Design for low power: sources of power dissipation, design transformations.",
        ),
        "CPE 513" to listOf(
            "data entities and relationships",
            "data modeling using Entity-Relation Diagrams: hierarchical, network and relational models of databases; query languages",
            "physical representation of data in secondary storage",
            "relational algebra and calculus as applied to the design of databases",
            "security and integrity in the context of concurrent use",
            "basic ethical issues associated with database design and use.",
        ),
        "CPE 511" to listOf(
            "Fading and shadowing, noise and interference effects",
            "source coding, modulation, error control coding, spread spectrum and multiplexing techniques for mobile communications",
            "capacity estimation and comparative (FDMA/TDMA/CDMA) analysis of PCN and Cellular Systems",
            "capacity estimation for wireless PABX and LAN systems.",
        ),
        "CPE 502" to listOf(
            "An introduction to the major concepts of operating systems and study of the interrelationships between the operating system and the architecture of computer systems.",
            "operating system structures",
            "concurrent programming techniques",
            "CPU scheduling",
            "deadlocks",
            "memory management",
            "file systems and protection.",
        ),
        "CPE 511" to listOf(
            "Characteristics and design of embedded systems.",
            "General purpose Processors; basic architecture data path controller memory operation instruction execution pipelining.",
            "programmer’s view; instruction set program & data memory space Rehisters I/O Interrupts operating systems microcontrollers.",
            "Standard Single-purpose Processors.",
            "Timers, counters & Watchdog timers UART, PWM, LCD controller Keypad Controllers Stepper Motor controller Analog-digital converters real-time clock.",
            "Custom single-purpose processors; combinational logic design sequential logic designs.",
            "Memories; memory hierarchy & Cache. Interfacing; timing diagrams, hardware protocol basics interfacing with a general purpose processor I/O addressing interrupts Direct Memory access. Arbitration, priority arbiter, daisy-chain arbitration Network-oriented arbitration methods, multi-level bus architecture. Computation models; sequential programmodel, state machine model finite-state machines, FSMD describing a system as a state machine.",
            "Formal models and specification languages for capturing system behaviour. Techniques for specification, exploration and refinement. System partitioning and hardware/software co-design.",
            "Tools for validation, verification, and simulation.",
            "Quality and performance metrics.",
        ),
        "CPE 506" to listOf(
            "Philosophy of artificial intelligence.",
            "AI programs and languages, representations and descriptions, exploiting constraints.",
            "Rule-based and heuristic systems.",
            "Applications to engineering.",
        ),
        "CPE 508" to listOf(
            "Introduction to reliability.",
            "Elementary reliability theory.",
            "Application to power systems and electronic components.",
            "Test characteristics of computer components-types of faults.",
            "Designing for higher reliability.",
            "Packaging mounting, ventilation.",
            "Protection from humidity, dust.",
        ),
        "CPE 510" to listOf(
            "Physics and property of semi-conductor including high field effects, carrier injection and semi-conductor surface phenomena, devices technology, bulk and expitaxial material growth and impurity control.",
            "Metal-semi-conductor interface properties, stability and methods of characterization controlled and Pre-requisite",
        ),
        "CPE 512" to listOf(
            "Basic concepts of distributed systems.",
            "Network architecture and internet routing.",
            "Message passing layers and remote procedure calls.",
            "Process migration.",
            "Distributed file systems and cache coherence.",
            "Server design for reliability, availability, and scalability.",
            "Internet security and electronic commerce.",
        ),
        "FEG 301" to listOf(
            "Fourier series",
            "Fourier transform",
            "power series solution of ordinary differential equations",
            "solutions of Laplace",
            "wave and heat equations by Fourier method",
        ),
        "FEG 302" to listOf(
            "Error analysis, Interpretation-its construction using both the Langrangian and Newton form",
            "Numerical differentiation and Integration",
            "trapezium and simpson’s rule solution of system of linear algebra equations",
            "Gaussian elimination with partial pivoting, jacobi and Guass- Seidel iterative method",
            "conditions for convergence solutions of non linear equations, Numerical solutions to ordinary differential equation",
            "Taylor series method, Euler’s method and Runge-Kutta method",
        ),
        "FEG 390" to listOf(
            """A more advanced industrial Programme; students this time are attached to appropriate Computer
/Electrical / Electronics / Agricultural / Mechanical Engineering facilities and industries to further
enhance practical approach to engineering through on-the job training."""
        ),
        "FEG 401" to listOf(
            "Professional use of English Language for letters, specification, descriptions, presentation of charts, graphs, tables, writing of proposals in reports.",
            "Case studies of major professional presentation reports and proposals",
        ),
        "FEG 403" to listOf(
            "Definition of entrepreneurship, entrepreneurship, reasons for study, relevance to engineering graduates, relevance to the Nigeria and global economy- prevailing situations, entrepreneurship as root of corporate strategy.",
            "Definition of business and scope small-scale enterprises.",
            "Factors affecting/ qualities of entrepreneurship.",
            "Sources of finance – short-/long term.",
            "Finance statement: cash flow. Risk analysis.",
            "Business growth: Growing concern.",
        ),
        "FEG 405" to listOf(
            "Descriptive statistics: mean, median, mode, chart and frequency distribution, probability and probability",
            "distribution, binomial distribution, linear and multiple regressions, correlation",
            "Analysis of Variance and degree of confidence",
            "Interpretation of statistical model",
            "computer applications in statistics",
        ),
        "FEG 490" to listOf(
            """
                A comprehensive internship programme in which students spend a full semester in approved engineering
                establishments (private and public) and industries. The exposure also provide opportunity for students to
                sharpen their technical writing skill through field reports, keeping log – books and prepared technical
                documents under close supervision of industry-based professionals and lecturers.
            """.trimIndent()
        ),
        "FEG 501" to listOf(
            "Law of management economics, management models, revenue of the firms, production decision, cost of production, profit analysis of the firms, pricing techniques location and localization of industries, industrial growth in Nigeria, the size of the firm integration and diversification marketing; demand and forecasting.",
            "Distributive trade in Nigeria, business finance, investment, capital budgeting and management control.",
            "Government policies and firm. Financing Technology: Capital equipment investment appraisal methods.",
            "Break even analysis.",
            "Budgeting and financial control.",
            "Fundamentals of cost accounting with emphasis on production costing.",
            "Areas of conflicts between Engineering valuation.",
            "Management: Oraganzational structure and behavior, engineer to engineer manager transition, managerial functions, principles and techniques of planning, forecasting organizing technical activities, project selection and management, style of leadership and management techniques.",
        ),
        "FEG 502" to listOf(
            "Law, ethics and conduct in engineering.",
            "Legal definitions and specifications.",
            "Application of business law to engineering.",
            "Industrial relations: Law of contract and engineering.",
            "Industrial relations: Law of contract and unionism, terms and conditions of employment.",
            "Intellectual property: patents, trademarks, copyrights, license and royalty.",
            "Contracts and contract documents, Technology transfer law.",
            "Safety and environmental regulations: health and safety law, environmental guidelines and laws.",
            "Technological responsibilities and liabilities: best practice, after sales.",
            "Technology impact assessment: effects on worker and staff, users and public safety, product lifetime and end of life disposal, legal and moral responsibilities.",
            "Role of the Engineer as a witness. Engineering business; types, the structure and functions of organizations.",
            "Management of engineer to manager transition. R & D management.",
            "Project selection and management.",
            "Capital investment policies.",
            "Technological collaborations: sub-contraction, consultancies, joint ventures and linkage programmes.",
            "Management of change and innovation.",
        )
    )
}